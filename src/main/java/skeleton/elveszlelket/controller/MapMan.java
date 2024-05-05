package skeleton.elveszlelket.controller;

import java.util.ArrayList;
import java.util.HashMap;
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
            Door d = new TwoWayDoor();
            connectRandom(room, d);
            
            // extra szobák súly szerint
            while (door > App.t.r.nextFloat()) {
                connectRandom(room, randomDoorType());
            }
        }

        // make map traversable
        Room startingRoom = map.get(0);
        for (Room room : map) {
            while(distance(startingRoom, room) < 0) {
                connectRandom(room, randomDoorType());
            }
        }

        // populate with items and calculate maxdist for later
        int maxdist = Integer.MIN_VALUE;
        for (Room room : map) {
            int dist = distance(startingRoom, room);
            if(dist > maxdist)
                maxdist = dist;
            while (item > App.t.r.nextFloat()) {
                Item item = getRandomItem();
                if(fals > App.t.r.nextFloat())
                    item.setFalse(true);
                room.addItem(item);
            }
            // apply curse 
            if(curse > App.t.r.nextFloat() && room != startingRoom)
                room.setCursed(true);
            // apply gas
            if(gas > App.t.r.nextFloat() && room != startingRoom)
                room.setGas(true);
        }

        // add students
        for (Student student : students) {
            startingRoom.addHuman(student);
            student.setCurrentRoom(startingRoom);
        }

        // Add teachers 
        for (Teacher teacher : teachers) {
            while (true) {
                Room rand = getRandomRoom();
                if(distance(startingRoom, rand) > maxdist/2) {
                    rand.addHuman(teacher);
                    teacher.setCurrentRoom(rand);
                    break;
                }
            }
        }

        // Add cleaners
        for (CleaningLady cleaningLady : cleaningLadies) {
            while (true) {
                Room rand = getRandomRoom();
                if(distance(startingRoom, rand) > maxdist/2) {
                    rand.addHuman(cleaningLady);
                    cleaningLady.setCurrentRoom(rand);
                    break;
                }
            }
        }

        // Add real logar
        for (Room room : map) {
            if(distance(startingRoom, room) == maxdist) {
                room.addItem(new Logar());
                break;
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

    private int distance(Room start, Room dest) {
        // HashMap<Room, Integer> table = new HashMap<>();
        // table.put(start, 0);
        // Room r = start;
        // List<Room> neigh = new ArrayList<>();
        // for (Door d : start.getDoors()) {
        //     Room n = d.getDest(r);
        //     if(n == r)
        //         continue;
        //     table.put(n, table.get(r));
        // }
        return -1;
    }

    private Room getRandomRoom() {
        return map.get(App.t.r.nextInt(size-1));
    }

    private void connectRandom(Room room, Door door) {
        int randindx = App.t.r.nextInt(size-1);
        while (room == map.get(randindx))
            randindx = App.t.r.nextInt(size-1);
        Room room2 = map.get(randindx);
        door.setRooms(room, room2);
        room.addDoor(door);
        room2.addDoor(door);
    }

    // egyirányú szoba sorsolás súllyal
    private Door randomDoorType() {
        Door d;
        if(oneway > App.t.r.nextFloat()) 
            d = new OneWayDoor();
        else
            d = new TwoWayDoor();
        return d;
    }


}
