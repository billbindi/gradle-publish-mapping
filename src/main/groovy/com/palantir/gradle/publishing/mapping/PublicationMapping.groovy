// Copyright 2015 Palantir Technologies
//
// Licensed under the Apache License, Version 2.0 (the "License")
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package com.palantir.gradle.publishing.mapping;

public class PublicationMapping {

  Map<String, List> map = [:]

  public void put(String publicationName, String repositoryName) {
    this.put(publicationName, [repositoryName])
  }

  public void put(String publicationName, List<String> repositoryNames) {
    map[publicationName] = repositoryNames
  }

  public void add(String publicationName, String repositoryName) {
    this.add(publicationName, [repositoryName])
  }

  public void add(String publicationName, List<String> repositoryNames) {
    if (map.containsKey(publicationName)) {
      map[publicationName] += repositoryNames
    } else {
      map[publicationName] = repositoryNames
    }
  }

  public boolean isWhitelisted(String publicationName, String repositoryName) {
    return !map.containsKey(publicationName) || map[publicationName].toSet().contains(repositoryName)
  }

  public boolean isBlacklisted(String publicationName, String repositoryName) {
    return map.containsKey(publicationName) && !map[publicationName].toSet().contains(repositoryName)
  }

}
