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
package uberunit.assertions;

/**
 * Understands how to validate the correctness of Java statements.
 *
 * @param <T> the type of expected value
 */
public class Assertion<T> {

        private final T actual;

        public Assertion(final T actual) {
                this.actual = actual;
        }

        public void isEqualTo(final T expected) throws Exception {
                if (actual == expected) {
                        return;
                }

                if (actual != null && actual.equals(expected)) {
                        return;
                }

                throw bomb(expected);
        }

        public void isNotEqualTo(final T expected) throws Exception {
                if (actual == expected) {
                        throw bomb(expected);
                }

                if (actual != null && actual.equals(expected)) {
                        throw bomb(expected);
                }
        }

        public void isNotNull() throws Exception {
                if (actual != null) {
                        return;
                }

                throw bomb("not null");
        }

        public void isNull() throws Exception {
                if (actual == null) {
                        return;
                }

                throw bomb(null);
        }

        private AssertionError bomb(final Object expected) {
                return new AssertionError(
                    "expected " + expected + ", but was " + actual);
        }
}
