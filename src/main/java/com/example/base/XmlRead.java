package com.example.base;

import lombok.Data;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class XmlRead {
    private void readXml(){
        try {
            Document document = new SAXReader().read(new File("D:\\workproject\\idea\\demo\\src\\main\\resources\\data_test\\monitoring_site.xml"));
            Element rootElement = document.getRootElement();
            Iterator<Element> elementIterator = rootElement.elementIterator();
            List<ImportSiteSH> siteList = new LinkedList<>();
            while (elementIterator.hasNext()){
                Element next = elementIterator.next();
                Element id = next.element("ID");
                Element otid = next.element("OTID");
                Element mc = next.element("MC");
                Element code = next.element("CODE");
                Element address = next.element("ADDRESS");
                Element latitude = next.element("LATITUDE");
                Element longitude = next.element("LONGITUDE");
                int id_data = Integer.parseInt(id.getText());
                int otid_data = Integer.parseInt(otid.getText());
                String code_data = code.getText();
                String mc_data = mc.getText();
                String address_data = address.getText();
                double latitude_data = Double.parseDouble(latitude.getText());
                double longitude_data = Double.parseDouble(longitude.getText());

                ImportSiteSH importSiteSH = new ImportSiteSH();
                importSiteSH.setAddress(address_data);
                importSiteSH.setCode(code_data);
                importSiteSH.setId(id_data);
                importSiteSH.setLatitude(latitude_data);
                importSiteSH.setLongitude(longitude_data);
                importSiteSH.setMc(mc_data);
                importSiteSH.setOtid(otid_data);

                siteList.add(importSiteSH);

            }
            System.out.println(siteList.size());
            for(int index=0;index < siteList.size() -1;index++){
                double latitude_1 = siteList.get(index).getLatitude();
                double longitude_1 = siteList.get(index).getLongitude();
                double latitude_2 = siteList.get(index + 1).getLatitude();
                double longitude_2 = siteList.get(index + 1).getLongitude();
                double distance = GetDistance(longitude_1, latitude_1, longitude_2, latitude_2);
                System.out.println(siteList.get(index).getId()+" : "+distance);

            }




        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        XmlRead xmlRead = new XmlRead();
        xmlRead.readXml();
    }

    private static final  double EARTH_RADIUS = 6378137;//赤道半径
    private static double rad(double d){
        return d * Math.PI / 180.0;
    }
    public static double GetDistance(double lon1,double lat1,double lon2, double lat2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lon1) - rad(lon2);
        double s = 2 *Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2)+Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
        s = s * EARTH_RADIUS;
        return s;//单位米
    }

    @Data
    class ImportSiteSH{
        public ImportSiteSH(){}
        private int id;
        private int otid;
        private String mc;
        private String code;
        private String address;
        private double latitude;
        private double longitude;
    }
}
