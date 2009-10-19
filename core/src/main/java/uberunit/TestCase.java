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

        private final ITest testCase;

        public TestCase(final Description description, final Setup setup,
            final TearDown tearDown, final ITest testCase) {
                this.description = description;
                this.setup = setup;
                this.tearDown = tearDown;
                this.testCase = testCase;
        }

        public void run(final Observer observer) {
                if (testCase.isIgnored()) {
                        observer.testCaseIgnored(description);
                        return;
                }

                test(observer);
        }

        public boolean isParallelizable() {
                return testCase.isParallelizable();
        }

        private void test(final Observer observer) {
                if (setUp(observer)) {
                        testCase(observer);
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

        private void testCase(final Observer observer) {
                try {
                        observer.inTestCase(description);
                        testCase.test();
                        observer.testCaseSucceeded(description);
                } catch (Exception e) {
                        observer.testCaseFailed(description, e);
                }
        }
}
