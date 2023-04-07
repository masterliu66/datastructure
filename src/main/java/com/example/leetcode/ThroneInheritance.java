package com.example.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * className ThroneInheritance
 * packageName com.example.leetcode
 * description
 *
 * @author CCC
 * @date 2021/6/20 22:06
 */
public class ThroneInheritance {

    public static void main(String[] args) {

        ThroneInheritance t = new ThroneInheritance("king"); // 继承顺序：king
        t.birth("king", "andy"); // 继承顺序：king > andy
        t.birth("king", "bob"); // 继承顺序：king > andy > bob
        t.birth("king", "catherine"); // 继承顺序：king > andy > bob > catherine
        t.birth("andy", "matthew"); // 继承顺序：king > andy > matthew > bob > catherine
        t.birth("bob", "alex"); // 继承顺序：king > andy > matthew > bob > alex > catherine
        t.birth("bob", "asha"); // 继承顺序：king > andy > matthew > bob > alex > asha > catherine
        // 返回 ["king", "andy", "matthew", "bob", "alex", "asha", "catherine"]
        System.out.println(t.getInheritanceOrder());
        t.death("bob"); // 继承顺序：king > andy > matthew > bob（已经去世）> alex > asha > catherine
        // 返回 ["king", "andy", "matthew", "alex", "asha", "catherine"]
        System.out.println(t.getInheritanceOrder());
    }

    private People king;

    private Map<String, People> cache;

    public ThroneInheritance(String kingName) {
        this.king = new People(kingName);
        this.cache = new HashMap<>(16);
        this.cache.put(kingName, this.king);
    }

    public void birth(String parentName, String childName) {

        People people = cache.get(parentName);

        People child = new People(childName);

        people.addChild(child);

        cache.put(childName, child);
    }

    public void death(String name) {

        People people = cache.get(name);

        people.setDeath(true);
    }

    public List<String> getInheritanceOrder() {

        List<String> inheritanceOrder = new ArrayList<>();

        king.inheritanceOrder(inheritanceOrder);

        return inheritanceOrder;
    }

    class People {

        String name;

        boolean death;

        List<People> children;

        public People(String name) {
            this.name = name;
            this.death = false;
            this.children = new ArrayList<>();
        }

        void addChild(People child) {
            this.children.add(child);
        }

        void setDeath(boolean death) {
            this.death = death;
        }

        People find(String name) {

            if (this.name.equals(name)) {
                return this;
            }

            People people = null;
            for (People child : children) {
                people = child.find(name);
                if (people != null) {
                    return people;
                }
            }

            return people;
        }

        void inheritanceOrder(List<String> inheritanceOrder) {

            if (!death) {
                inheritanceOrder.add(name);
            }

            for (People child : children) {
                child.inheritanceOrder(inheritanceOrder);
            }
        }

    }

}
