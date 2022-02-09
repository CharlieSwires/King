package com.charlie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
public class RController {
    @Autowired
    private KingTest kingTest;
    
    public RController(KingTest kt) {
        kingTest = kt;
    }
    @GetMapping(path="{userid}/login")
    public ResponseEntity<String> get(@PathVariable("userid") Integer userid) {
        
        return new ResponseEntity<String>(kingTest.getSessionKey(userid), HttpStatus.OK);
    }
    @GetMapping(path="{levelid}/highscorelist")
    public ResponseEntity<String> gethighscorelist(@PathVariable("levelid") Integer levelid) {
        
        return new ResponseEntity<String>(kingTest.getLevelScores(levelid), HttpStatus.OK);
    }
    @SuppressWarnings("rawtypes")
    @PostMapping(path="{levelid}/score", consumes="application/json")
    public ResponseEntity post(@PathVariable("levelid") Integer levelid,@RequestParam String sessionkey, @RequestBody PayloadBean score) {
        if (!kingTest.isValidSession(sessionkey)) return new ResponseEntity(HttpStatus.OK);
        Integer userid = kingTest.getUserid(sessionkey);
        kingTest.recordLevelAndScore(userid, levelid, score.getScore());
        return new ResponseEntity(HttpStatus.OK);
    }    

}
