package com.charlie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import com.charlie.KingTest;
import com.charlie.PayloadBean;
import com.charlie.RController;

import junit.framework.Assert;

class Tests {
    private RController rController;
    private KingTest kt;
    
    @BeforeEach
    void setUp() throws Exception {
        kt = new KingTest();
        rController = new RController(kt);
    }

    @Test
    void test() {
        ResponseEntity<String> session1 = rController.get(3141);
        PayloadBean pb = new PayloadBean();
        pb.setScore(1234);
        rController.post(1, session1.getBody(), pb);
        ResponseEntity<String> session2 = rController.get(3142);
        pb.setScore(1235);
        rController.post(1, session2.getBody(), pb);
        ResponseEntity<String> session3 = rController.get(3143);
        pb.setScore(1236);
        rController.post(1, session3.getBody(), pb);
        ResponseEntity<String> session4 = rController.get(3144);
        pb.setScore(1237);
        rController.post(1, session4.getBody(), pb);
        ResponseEntity<String> session5 = rController.get(3145);
        pb.setScore(1238);
        rController.post(1, session5.getBody(), pb);
        ResponseEntity<String> session6 = rController.get(3146);
        pb.setScore(1239);
        rController.post(1, session6.getBody(), pb);
        ResponseEntity<String> session7 = rController.get(3147);
        pb.setScore(12310);
        rController.post(1, session7.getBody(), pb);
        ResponseEntity<String> session8 = rController.get(3148);
        pb.setScore(12311);
        rController.post(1, session8.getBody(), pb);
        ResponseEntity<String> session9 = rController.get(3149);
        pb.setScore(12312);
        rController.post(1, session9.getBody(), pb);
        ResponseEntity<String> session10 = rController.get(31410);
        pb.setScore(12313);
        rController.post(1, session10.getBody(), pb);
        ResponseEntity<String> session11 = rController.get(3111);
        pb.setScore(12314);
        rController.post(1, session11.getBody(), pb);
        ResponseEntity<String> session12 = rController.get(31412);
        pb.setScore(12315);
        rController.post(1, session12.getBody(), pb);
        ResponseEntity<String> session13 = rController.get(31413);
        pb.setScore(12316);
        rController.post(1, session13.getBody(), pb);
        ResponseEntity<String> session14 = rController.get(31414);
        pb.setScore(12317);
        rController.post(1, session14.getBody(), pb);
        ResponseEntity<String> session15 = rController.get(31415);
        pb.setScore(12318);
        rController.post(1, session15.getBody(), pb);
        ResponseEntity<String> session16 = rController.get(31416);
        pb.setScore(12319);
        rController.post(1, session16.getBody(), pb);
        ResponseEntity<String> session17 = rController.get(31417);
        pb.setScore(12320);
        rController.post(1, session17.getBody(), pb);
        ResponseEntity<String> session18 = rController.get(31418);
        pb.setScore(12321);
        rController.post(1, session18.getBody(), pb);
        ResponseEntity<String> session19 = rController.get(31419);
        pb.setScore(12322);
        rController.post(1, session19.getBody(), pb);
        ResponseEntity<String> session20 = rController.get(31420);
        pb.setScore(12323);
        rController.post(1, session20.getBody(), pb);
        pb.setScore(12324);
        rController.post(1, session20.getBody(), pb);//handles same user
        System.out.println(rController.gethighscorelist(1).getBody());
        Assert.assertEquals("Max 15", rController.gethighscorelist(1).getBody(),"12324=31420, 12322=31419, 12321=31418, 12320=31417, 12319=31416, 12318=31415, 12317=31414, 12316=31413, 12315=31412, 12314=3111, 12313=31410, 12312=3149, 12311=3148, 12310=3147, 1239=3146");
    }

}
