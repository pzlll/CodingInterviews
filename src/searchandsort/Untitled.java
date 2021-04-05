package searchandsort;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Untitled {


    public static void main(String[] args) {
        User u1 = new User(1,12);
        User u2 = new User(2, 3);
        User u3 = new User(3,85);
        User u4 = new User(4, 25);
        User u5 = new User(5,9);
        User u6 = new User(6,99);
        List<User> list = new ArrayList<>();
        list.add(u1);
        list.add(u2);
        list.add(u3);
        list.add(u4);
        list.add(u5);
        list.add(u6);
        sort(list, 0, list.size() - 1);

        for (User user :
                list) {
            System.out.println(user.getForce());
        }
    }

    public static void sort(List<User> users, int start, int end) {
        if(start >= end) {
            return ;
        }
        int partition = quickSort(users, start, end);
        sort(users,start, partition-1);
        sort(users, partition+1, end);

    }

    public static boolean drawCard(User user) {
        if(user.getCount() == 30) {
            user.setCount(0);
            return true;
        }
        Random r = new Random();
        int i = r.nextInt(20);
        if(i == 0) {
            user.setCount(0);
            return true;
        }else {
            user.setCount(user.getCount()+1);
            return false;
        }

    }


    public static int  quickSort(List<User> users, int start, int end){
        if(start >= end) {
            return 0;
        }
        User user = users.get(start);
        while(start < end) {
            while(start < end && users.get(end).getForce() >= user.getForce()) {
                end--;
            }
            users.set(start, users.get(end));
            while(start < end && users.get(start).getForce() <= user.getForce()) {
                start++;
            }
            users.set(end, users.get(start));
        }

        users.set(start, user);
        return  start;
    }
}

class User{
    private int uid;
    private int force;
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public User(int uid, int force) {
        this.uid = uid;
        this.force = force;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }
}
