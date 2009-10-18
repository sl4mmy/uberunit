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
 * Represents how to monitor the progress of running tests.
 */
public interface Observer {

        void cancelled(final Description description);

        void inSetUp(final Description description);

        void setUpFailed(final Description description,
            final Exception failure);

        void inTearDown(final Description description);

        void tearDownFailed(final Description description,
            final Exception failure);

        void inTestCase(final Description description);

        void testCaseFailed(final Description description,
            final Exception failure);

        void testCaseIgnored(final Description description);

        void testCaseSucceeded(final Description description);
}
