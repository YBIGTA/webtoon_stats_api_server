/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ybigta.webtoon_recommendation.analysis.utils;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CosineSimilarity {

	public Double cosineSimilarity(Map<String, Integer> leftVector, Map<String, Integer> rightVector) {

		if (leftVector == null || rightVector == null) {
			throw new IllegalArgumentException("Vectors must not be null");
		}

		Set<String> intersection = getIntersection(leftVector, rightVector);

		double dotProduct = dot(leftVector, rightVector, intersection);
		double d1 = 0.0d;

		for (Integer value : leftVector.values()) {
			d1 += Math.pow(value, 2);
		}

		double d2 = 0.0d;
		for (Integer value : rightVector.values()) {
			d2 += Math.pow(value, 2);
		}

		double cosineSimilarity;

		if (d1 <= 0.0 || d2 <= 0.0) {
			cosineSimilarity = 0.0;
		} else {
			cosineSimilarity = (double) (dotProduct / (double) (Math.sqrt(d1) * Math.sqrt(d2)));
		}
		
		return cosineSimilarity;
	}

	/**
	 * Returns a set with strings common to the two given maps.
	 *
	 * @param leftVector
	 *            left vector map
	 * @param rightVector
	 *            right vector map
	 * @return common strings
	 */
	private Set<String> getIntersection(Map<String, Integer> leftVector, Map<String, Integer> rightVector) {
		Set<String> intersection = new HashSet<String>(leftVector.keySet());
		intersection.retainAll(rightVector.keySet());
		return intersection;
	}

	/**
	 * Computes the dot product of two vectors. It ignores remaining elements.
	 * It means that if a vector is longer than other, then a smaller part of it
	 * will be used to compute the dot product.
	 *
	 * @param leftVector
	 *            left vector
	 * @param rightVector
	 *            right vector
	 * @param intersection
	 *            common elements
	 * @return the dot product
	 */
	private double dot(Map<String, Integer> leftVector, Map<String, Integer> rightVector, Set<String> intersection) {
		long dotProduct = 0;
		for (String key : intersection) {
			dotProduct += leftVector.get(key) * rightVector.get(key);
		}
		return dotProduct;
	}
}
