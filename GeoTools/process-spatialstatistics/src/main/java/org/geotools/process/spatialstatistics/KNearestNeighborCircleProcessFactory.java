/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2020, Open Source Geospatial Foundation (OSGeo)
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation;
 *    version 2.1 of the License.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */
package org.geotools.process.spatialstatistics;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import org.geotools.api.data.Parameter;
import org.geotools.api.util.InternationalString;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.feature.NameImpl;
import org.geotools.process.Process;
import org.geotools.process.spatialstatistics.enumeration.DistanceUnit;
import org.geotools.util.logging.Logging;

/**
 * KNearestNeighborCircleProcessFactory
 * 
 * @author Minpa Lee, MangoSystem
 * 
 * @source $URL$
 */
public class KNearestNeighborCircleProcessFactory extends SpatialStatisticsProcessFactory {
    protected static final Logger LOGGER = Logging
            .getLogger(KNearestNeighborCircleProcessFactory.class);

    private static final String PROCESS_NAME = "KNearestNeighborCircle";

    /*
     * KNearestNeighborCircle(SimpleFeatureCollection inputFeatures, SimpleFeatureCollection nearFeatures, Integer neighbor, Double maximumDistance,
     * DistanceUnit distanceUnit): SimpleFeatureCollection
     */

    public KNearestNeighborCircleProcessFactory() {
        super(new NameImpl(NAMESPACE, PROCESS_NAME));
    }

    @Override
    public Process create() {
        return new KNearestNeighborCircleProcess(this);
    }

    @Override
    public InternationalString getTitle() {
        return getResource("KNearestNeighborCircle.title");
    }

    @Override
    public InternationalString getDescription() {
        return getResource("KNearestNeighborCircle.description");
    }

    /** inputFeatures */
    public static final Parameter<SimpleFeatureCollection> inputFeatures = new Parameter<SimpleFeatureCollection>(
            "inputFeatures", SimpleFeatureCollection.class,
            getResource("KNearestNeighborCircle.inputFeatures.title"),
            getResource("KNearestNeighborCircle.inputFeatures.description"), true, 1, 1, null,
            null);

    /** nearFeatures */
    public static final Parameter<SimpleFeatureCollection> nearFeatures = new Parameter<SimpleFeatureCollection>(
            "nearFeatures", SimpleFeatureCollection.class,
            getResource("KNearestNeighborCircle.nearFeatures.title"),
            getResource("KNearestNeighborCircle.nearFeatures.description"), true, 1, 1, null, null);

    /** neighbor */
    public static final Parameter<Integer> neighbor = new Parameter<Integer>("neighbor",
            Integer.class, getResource("KNearestNeighborCircle.neighbor.title"),
            getResource("KNearestNeighborCircle.neighbor.description"), false, 0, 1,
            Integer.valueOf(1), null);

    /** maximumDistance */
    public static final Parameter<Double> maximumDistance = new Parameter<Double>("maximumDistance",
            Double.class, getResource("KNearestNeighborCircle.maximumDistance.title"),
            getResource("KNearestNeighborCircle.maximumDistance.description"), false, 0, 1,
            Double.valueOf(0d), null);

    /** distanceUnit */
    public static final Parameter<DistanceUnit> distanceUnit = new Parameter<DistanceUnit>(
            "distanceUnit", DistanceUnit.class,
            getResource("KNearestNeighborCircle.distanceUnit.title"),
            getResource("KNearestNeighborCircle.distanceUnit.description"), false, 0, 1,
            DistanceUnit.Default, null);

    @Override
    protected Map<String, Parameter<?>> getParameterInfo() {
        HashMap<String, Parameter<?>> parameterInfo = new LinkedHashMap<String, Parameter<?>>();
        parameterInfo.put(inputFeatures.key, inputFeatures);
        parameterInfo.put(nearFeatures.key, nearFeatures);
        parameterInfo.put(neighbor.key, neighbor);
        parameterInfo.put(maximumDistance.key, maximumDistance);
        parameterInfo.put(distanceUnit.key, distanceUnit);
        return parameterInfo;
    }

    /** result */
    public static final Parameter<SimpleFeatureCollection> RESULT = new Parameter<SimpleFeatureCollection>(
            "result", SimpleFeatureCollection.class,
            getResource("KNearestNeighborCircle.result.title"),
            getResource("KNearestNeighborCircle.result.description"));

    static final Map<String, Parameter<?>> resultInfo = new TreeMap<String, Parameter<?>>();
    static {
        resultInfo.put(RESULT.key, RESULT);
    }

    @Override
    protected Map<String, Parameter<?>> getResultInfo(Map<String, Object> parameters)
            throws IllegalArgumentException {
        return Collections.unmodifiableMap(resultInfo);
    }

}
