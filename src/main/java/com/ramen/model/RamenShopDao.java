package com.ramen.model;

import com.ramen.entity.RamenShop;
import java.util.ArrayList;
import java.util.List;

public class RamenShopDao {
    public List<RamenShop> findAll() {
        List<RamenShop> list = new ArrayList<>();
        list.add(new RamenShop("ラーメン一郎", "東京都新宿区", "濃厚豚骨スープが自慢"));
        list.add(new RamenShop("ラーメン二郎", "東京都渋谷区", "野菜たっぷりガッツリ系"));
        list.add(new RamenShop("ラーメン三郎", "東京都豊島区", "あっさり醤油味"));
        return list;
    }
}
