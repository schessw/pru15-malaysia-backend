package com.malaysiapru15.result.parliament;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/v1")
public class ParliamentController {
    private final ParliamentService parliamentService;

    @Autowired
    public ParliamentController(ParliamentService parliamentService) {
        this.parliamentService = parliamentService;
    }

    @GetMapping(path="/parliament")
    public List<Parliament> getParliaments() {
        return parliamentService.getParliaments();
    }

    @PostMapping(path="/parliament")
    public void addParliament(@RequestBody Parliament parliament) {
        parliamentService.addParliament(parliament);
    }

    @PutMapping(path="/parliament/{parliament_id}")
    public void updateParliament(@RequestBody Parliament parliament,
                                 @PathVariable("parliament_id") Long parliament_id) {
        parliamentService.updateParliament(parliament, parliament_id);
    }

    @DeleteMapping(path="/parliament/{parliament_id}")
    public void deleteParliament(@PathVariable("parliament_id") Long parliament_id) {
        parliamentService.deleteParliamentById(parliament_id);
    }
}
