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
package uberunit.suites;

import uberunit.Test;
import uberunit.internal.Hierarchy;
import uberunit.runners.MultiThreadedRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Understands how to schedule many tests at once.
 */
public class Suite implements Hierarchy<Suite> {

        private final List<Suite> children;

        private final List<Test> tests;

        public Suite() {
                this.children = new ArrayList<Suite>();
                this.tests = new ArrayList<Test>();
        }

        public Suite(final Suite parent) {
                this();

                parent.addChild(this);
        }

        public Suite(final List<Test> tests) {
                this();

                this.tests.addAll(tests);
        }

        public Suite(final List<Test> tests,
            final Suite parent) {
                this(parent);

                this.tests.addAll(tests);
        }

        public void start(final MultiThreadedRunner runner) {
                runner.scheduleAll(tests);

                for (Suite child : children) {
                        child.start(runner);
                }
        }

        public boolean addChild(final Suite child) {
                return children.add(child);
        }
}
