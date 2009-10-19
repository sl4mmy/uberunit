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
package uberunit.setups;

import uberunit.Setup;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Understands how to initialize tests by sending messages to objects
 * via reflection.
 */
public class JavaMethodBasedSetup implements Setup {

        private final List<Setup> children;

        private final Object testClassInstance;

        private final Method setupMethod;

        private final Object[] arguments;

        public JavaMethodBasedSetup(final Object testClassInstance,
            final Method setupMethod, final Object[] arguments) {
                this.testClassInstance = testClassInstance;
                this.setupMethod = setupMethod;
                this.arguments = arguments;

                this.children = new ArrayList<Setup>();
        }

        public JavaMethodBasedSetup(final Setup parent,
            final Object testClassInstance,
            final Method setupMethod, final Object[] arguments) {
                this(testClassInstance, setupMethod, arguments);

                parent.addChild(this);
        }

        public void setUp() throws Exception {
                for (Setup child : children) {
                        child.setUp();
                }

                setupMethod.invoke(testClassInstance, arguments);
        }

        public boolean addChild(final Setup child) {
                return children.add(child);
        }
}
