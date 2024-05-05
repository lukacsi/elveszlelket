package skeleton.elveszlelket.controller;

import java.util.ArrayList;
import java.util.List;
import skeleton.elveszlelket.*;
import skeleton.elveszlelket.door.Door;
import skeleton.elveszlelket.door.OneWayDoor;
import skeleton.elveszlelket.door.TwoWayDoor;
import skeleton.elveszlelket.item.AirFreshener;
import skeleton.elveszlelket.item.Beer;
import skeleton.elveszlelket.item.Camember;
import skeleton.elveszlelket.item.FFP2Mask;
import skeleton.elveszlelket.item.Item;
import skeleton.elveszlelket.item.Logar;
import skeleton.elveszlelket.item.Rag;
import skeleton.elveszlelket.item.TVSZ;
import skeleton.elveszlelket.item.Transistor;

public class MapMan {
    private int size;
    private float curse, gas, fals, item, door, oneway;
    private List<Teacher> teachers;
    private List<Student> students;
    private List<CleaningLady> cleaningLadies;
    private List<Room> map;

    public MapMan(int size, float curse, float gas, float fals, float item, float door, float oneway, List<Teacher> teachers, List<Student> students, List<CleaningLady> cleaningLadies) {
        this.teachers = teachers;
        this.cleaningLadies = cleaningLadies;
        this.students = students;
        this.size = size;
        this.curse = curse;
        this.gas = gas;
        this.fals = fals;
        this.item = item;
        this.door = door;
        this.oneway = oneway;
        map = new ArrayList<>();
    }

    public void init() {
        // Annyi szoba amekkora a size
        for(int i = 0; i < size; i++) {
            map.add(new Room());
        }

        // minden szobából egy ajtó egy random szobába
        for (Room room : map) {
            int randindx = App.t.r.nextInt(size-1);
            while (room == map.get(randindx))
                randindx = App.t.r.nextInt(size-1);
            Room room2 = map.get(randindx);

            TwoWayDoor d = new TwoWayDoor();

            d.setRooms(room, room2);
            room.addDoor(d);
            room2.addDoor(d);
        }

        // extra szábák súly szerint
        for (Room room : map) {
            // amíg a random szám az adott értéket alultalálja
            while (door > App.t.r.nextFloat()) {
                Door d;
                int randindx = App.t.r.nextInt(size-1);
                while (room == map.get(randindx))
                    randindx = App.t.r.nextInt(size-1);
                Room room2 = map.get(randindx);

                // egyirányú szoba sorsolás súllyal
                if(oneway > App.t.r.nextFloat()) 
                    d = new OneWayDoor();
                else
                    d = new TwoWayDoor();
                
                d.setRooms(room, room2);
                room.addDoor(d);
                room2.addDoor(d);
            }
        }
        for (Room room : map) {
            while (item > App.t.r.nextFloat()) {
                Item item = getRandomItem();
                if(fals > App.t.r.nextFloat())
                    item.setFalse(true);
                room.addItem(item);
            }
        }
        
    }

    private Item getRandomItem() {
        Item item;
        float rand = App.t.r.nextFloat();
        if(rand < 0.2) {
            item = new Beer();
        } else if (rand < 0.3) {
            item = new AirFreshener();
        } else if (rand < 0.4) {
            item = new Camember();
        } else if (rand < 0.5) {
            item = new FFP2Mask();
        } else if (rand < 0.6) {
            item = new Logar();
            // igazi logart később rakjuk le
            item.setFalse(true);
        } else if (rand < 0.7) {
            item = new Rag();
        } else if (rand < 0.9) {
            item = new Transistor();
        } else {
            item = new TVSZ();
        }
        return item;
    }

}
