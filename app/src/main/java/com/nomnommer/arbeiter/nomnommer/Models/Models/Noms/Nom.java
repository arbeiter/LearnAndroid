package com.nomnommer.arbeiter.nomnommer.Models.Models.Noms;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aureliano on 12/23/15.
 *    a. id string
 b. name string
 d. Categories list<String>
 e. snippet_image_url string
 f. location.coordinate dict
 g. address
 */
public class Nom {
        public String name;
        public List<String> categories = new ArrayList<String>();
        public String snippet_image_url;
        public String address;
}
