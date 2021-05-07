// Copyright 2021 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.firebase.perf.logging;

public final class ConsoleUrlGenerator {
  private static final String URL_BASE_PATH = "https://console.firebase.google.com";
  private static final String UTM_MEDIUM = "android-ide";
  private static final String UTM_SOURCE = "perf-android-sdk";

  /**
   * Generate the console URL for Firebase Performance dashboard page.
   *
   * @param projectId the Firebase project ID
   * @param packageName the name of this application's package
   */
  public static String generateDashboardUrl(String projectId, String packageName) {
    return String.format(
        "%s/project/%s/performance/app/android:%s/trends?utm_medium=%s",
        URL_BASE_PATH, projectId, packageName, UTM_MEDIUM);
  }

  /**
   * Generate the console URL for the custom trace.
   *
   * @param projectId the Firebase project ID
   * @param packageName the name of this application's package
   * @param name the trace name
   */
  public static String generateCustomTraceUrl(String projectId, String packageName, String name) {
    return String.format(
        "%s/project/%s/performance/app/android:%s/metrics/trace/DURATION_TRACE/%s?utm_source=%s&utm_medium=%s",
        URL_BASE_PATH, projectId, packageName, name, UTM_SOURCE, UTM_MEDIUM);
  }

  /**
   * Generate the console URL for the screen trace.
   *
   * @param projectId the Firebase project ID
   * @param packageName the name of this application's package
   * @param name the trace name
   */
  public static String generateScreenTraceUrl(String projectId, String packageName, String name) {
    return String.format(
        "%s/project/%s/performance/app/android:%s/metrics/trace/SCREEN_TRACE/%s?utm_source=%s&utm_medium=%s",
        URL_BASE_PATH, projectId, packageName, name, UTM_SOURCE, UTM_MEDIUM);
  }
}