package org.example.controllers;

import org.example.entity.Group;
import org.example.repository.GroupRepository;
import org.example.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/groups")
public class GroupController {
    private List<Group> groups = new ArrayList<>();


    @Autowired
    private GroupService groupService;
    @PostMapping("/create")
    public String createGroup(@RequestParam String groupName) {
        Group group = new Group(groupName);
        groups.add(group);
        return "Group created successfully!";
    }


    @PostMapping
    public Group createGroup(@RequestBody Group group) {
        return groupService.saveGroup(group);
    }

    @DeleteMapping("/{id}")
    public void deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
    }

    @GetMapping
    public List<Group> getAllGroups() {
        return groupService.getAllGroups();
    }

    @GetMapping("/filter")
    public List<Group> filterGroups(@RequestParam Map<String, String> filters) {
        return groupService.filterGroups(filters);
    }

}

