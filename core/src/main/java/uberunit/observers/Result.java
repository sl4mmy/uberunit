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

import java.util.Date;

/**
 * Understands how to track the outcome of running tests.
 */
public class Result {

        private final Date startedAt;

        private Date finishedAt;

        private final Date setUpStartedAt;

        private Date setUpFinishedAt;

        private Date tearDownStartedAt;

        private Date tearDownFinishedAt;

        private Date testStartedAt;

        private Date testFinishedAt;

        private Status result;

        public enum Status {

                CANCELLED, FAILED, IGNORED, SUCCEEDED;
        }

        public Result() {
                final Date now = new Date();
                this.startedAt = now;
                this.setUpStartedAt = now;
        }

        public void cancelled() {
                this.finishedAt = new Date();
        }
}
