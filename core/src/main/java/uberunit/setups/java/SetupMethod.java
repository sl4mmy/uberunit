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
package uberunit.setups.java;

import uberunit.Setup;
import uberunit.descriptions.Description;

import java.lang.reflect.Method;

/**
 * Understands how to send messages to Java objects to initialize
 * tests.
 */
public class SetupMethod implements Setup {

        private final Object instance;

        private final Method method;

        private final Object[] arguments;

        private Description description;

        public SetupMethod(final Object instance, final Method method,
            final Object[] arguments, final Description description) {
                this.instance = instance;
                this.method = method;
                this.arguments = arguments;
                this.description = description;
        }

        public Description getDescription() {
                return description;
        }

        public boolean isIgnored() {
                return false;
        }

        public void setUp() throws Exception {
                method.invoke(instance, arguments);
        }
}
