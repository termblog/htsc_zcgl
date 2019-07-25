package com.htsc.zcgl.util.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by shild on 2015/10/15.
 */
public class Grid {
    private String id;
    private int total;
    private List<String> hds;
    private List<String> ids;
    private List<Map> editors;
    private List<Boolean> hiddens;
    private Object rows;

    public Grid(String id, int total) {
        this.id = id;
        this.total = total;
        this.rows = new ArrayList();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<String> getHds() {
        return hds;
    }

    public void setHds(List<String> hds) {
        this.hds = hds;
    }

    public List<Map> getEditors() {
        return editors;
    }

    public void setEditors(List<Map> editors) {
        this.editors = editors;
    }

    public List<Boolean> getHiddens() {
        return hiddens;
    }

    public void setHiddens(List<Boolean> hiddens) {
        this.hiddens = hiddens;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public Object getRows() {
        return rows;
    }

    public void setRows(Object rows) {
        this.rows = rows;
    }


}

//boolean [] bool_list = {true,false};
//List<Map> editors = getEditorList(bool_list);

//Boolean [] hiddens = {false,false,true};
//appinfo.setHiddens(Arrays.asList(hiddens));

//Collections.sort(rows, new MapComparator());//sort test ok

////排序 小到大
//Collections.sort(rows,new Comparator<Map>(){
//public int compare(Map arg0, Map arg1) {
//        String str1 =  arg0.get("cpu").toString();
//        String str2 = arg1.get("cpu").toString();
//        return str1.compareTo(str2);//加负号就是从大到小
//        }
//      });
