package com.jgq.nettysourcecode;

import io.netty.util.Recycler;

/**
 * @Author: Created by carrot
 * 2020/11/19 13:59
 */
public class RecyclerTest {

    private static final Recycler<User> RECYCLER = new Recycler<User>() {
        @Override
        protected User newObject(Handle<User> handle) {
            return new User(handle);
        }
    };

    private static class User{
        private final Recycler.Handle<User> handle;

        public User(Recycler.Handle<User> handle){
            this.handle = handle;
        }

        public void recycle(){
            handle.recycle(this);
        }
    }

    public static void main(String[] args) {
        User user = RECYCLER.get();
        user.recycle();
        User user1 = RECYCLER.get();
        System.out.println(user == user1);

    }
}
