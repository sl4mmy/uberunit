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
package uberunit.runners;

import uberunit.TestCase;
import uberunit.observers.Observer;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Understands how to execute multiple test cases in parallel using
 * Java's standard concurrency features.
 */
public class MultiThreadedRunner {

        private final Observer observer;

        private final ExecutorService threadPool;

        public MultiThreadedRunner(final Observer observer) {
                this.observer = observer;
                this.threadPool = Executors.newCachedThreadPool();
        }

        public void finish() {
                try {
                        threadPool.shutdown();
                        threadPool.awaitTermination(5, SECONDS);
                } catch (InterruptedException e) {
                        threadPool.shutdownNow();
                }
        }

        public void scheduleAll(final List<TestCase> testCases) {
                for (final TestCase testCase : testCases) {
                        threadPool.submit(new Runnable() {
                                public void run() {
                                        testCase.run(observer);
                                }
                        });
                }
        }
}
