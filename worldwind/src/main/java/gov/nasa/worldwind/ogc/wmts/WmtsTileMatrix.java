/*
 * Copyright (c) 2017 United States Government as represented by the Administrator of the
 * National Aeronautics and Space Administration. All Rights Reserved.
 */

package gov.nasa.worldwind.ogc.wmts;

import gov.nasa.worldwind.util.xml.XmlModel;

public class WmtsTileMatrix extends XmlModel {

    protected int id;

    protected double scaleDenominator;

    protected double minx;

    protected double maxy;

    protected int tileWidth;

    protected int tileHeight;

    protected int matrixWidth;

    protected int matrixHeight;

    public boolean isWwaCompatible() {
        if (matrixWidth % 2 != 0) {
            return false;
        } else if (matrixHeight % 2 != 0) {
            return false;
        } else if ((matrixHeight * 2) != matrixWidth) {
            return false;
        } else return tileWidth == tileHeight;
    }

    protected void parseTopLeftCorner(String value) {
        String[] values = value.split("\\s+");

        if (values.length == 2) {
            this.minx = Double.parseDouble(values[0]);
            this.maxy = Double.parseDouble(values[1]);
        } else {
            // TODO log
        }
    }

    @Override
    protected void parseField(String keyName, Object value) {
        if (keyName.equals("Identifier")) {
            this.id = Integer.parseInt((String) value);
        } else if (keyName.equals("ScaleDenominator")) {
            this.scaleDenominator = Double.parseDouble((String) value);
        } else if (keyName.equals("TopLeftCorner")) {
            this.parseTopLeftCorner((String) value);
        } else if (keyName.equals("TileWidth")) {
            this.tileWidth = Integer.parseInt((String) value);
        } else if (keyName.equals("TileHeight")) {
            this.tileHeight = Integer.parseInt((String) value);
        } else if (keyName.equals("MatrixWidth")) {
            this.matrixWidth = Integer.parseInt((String) value);
        } else if (keyName.equals("MatrixHeight")) {
            this.matrixHeight = Integer.parseInt((String) value);
        }
    }
}