/*
 * Copyright (c) 2009, Kent R. Spillner <kspillner@acm.org>
 *
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
 * ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
 * OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */
package uberunit;

import uberunit.descriptions.Description;
import uberunit.observers.Observer;

/**
 * Understands the process of automatically verifying code correctness.
 */
public class TestCase {

        private final Description description;

        private final Setup setup;

        private final TearDown tearDown;

        private final Test test;

        public TestCase(final Description description,
            final Setup setup, final TearDown tearDown,
            final Test test) {
                this.description = description;
                this.setup = setup;
                this.tearDown = tearDown;
                this.test = test;
        }

        public void run(final Observer observer) {
                if (test.isIgnored()) {
                        observer.testIgnored(description);
                        return;
                }

                process(observer);
        }

        public boolean isParallelizable() {
                return test.isParallelizable();
        }

        private void process(final Observer observer) {
                if (setUp(observer)) {
                        test(observer);
                }

                tearDown(observer);
        }

        private boolean setUp(final Observer observer) {
                try {
                        observer.inSetUp(description);
                        setup.setUp();
                        return true;
                } catch (Exception e) {
                        observer.setUpFailed(description, e);
                        return false;
                }
        }

        private void tearDown(final Observer observer) {
                try {
                        observer.inTearDown(description);
                        tearDown.tearDown();
                } catch (Exception e) {
                        observer.tearDownFailed(description, e);
                }
        }

        private void test(final Observer observer) {
                try {
                        observer.inTest(description);
                        test.test();
                        observer.testSucceeded(description);
                } catch (Exception e) {
                        observer.testFailed(description, e);
                }
        }
}
