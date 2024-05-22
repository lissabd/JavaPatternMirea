package org.example.controllers;

import org.example.models.Group;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/groups")
// обрабатывает запросы и возвращает данные в json формате
public class GroupController {
    private List<Group> groups = new ArrayList<>();

    @PostMapping("/create")
    public String createGroup(@RequestParam String groupName) {
        Group group = new Group(groupName);
        groups.add(group);
        return "Group created successfully!";
    }

    @GetMapping("/all")
    public List<Group> getAllGroups() {
        return groups;
    }

    @DeleteMapping("/delete")
    public String deleteGroup(@RequestParam int index) {
        if (index >= 0 && index < groups.size()) {
            groups.remove(index);
            return "Group deleted successfully!";
        } else {
            return "Invalid index!";
        }
    }
}
