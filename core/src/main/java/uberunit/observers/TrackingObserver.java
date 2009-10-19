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
package uberunit.observers;

import uberunit.descriptions.Description;

/**
 * Understands how to monitor the progress of every test case.
 */
public class TrackingObserver implements Observer {

        public void cancelled(final Description description) {
        }

        public void inSetUp(final Description description) {
        }

        public void setUpFailed(final Description description,
            final Exception failure) {
        }

        public void inTearDown(final Description description) {
        }

        public void tearDownFailed(final Description description,
            final Exception failure) {
        }

        public void inTest(final Description description) {
        }

        public void testFailed(final Description description,
            final Exception failure) {
        }

        public void testIgnored(final Description description) {
        }

        public void testSucceeded(final Description description) {
        }
}
