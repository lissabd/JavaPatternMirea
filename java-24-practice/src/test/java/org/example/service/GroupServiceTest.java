package org.example.service;

import org.example.entity.Group;
import org.example.repository.GroupRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GroupServiceTest {

    @Mock
    private GroupRepository groupRepository;

    @InjectMocks
    private GroupService groupService;

    public GroupServiceTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllGroups() {
        // Создание тестовых данных
        Group group1 = new Group();
        group1.setId(1L);
        group1.setGroupName("Group 1");

        Group group2 = new Group();
        group2.setId(2L);
        group2.setGroupName("Group 2");

        when(groupRepository.findAll()).thenReturn(List.of(group1, group2));

        // Вызов метода, который мы тестируем
        List<Group> groups = groupService.getAllGroups();

        // Проверка результатов
        assertEquals(2, groups.size());
        assertEquals("Group 1", groups.get(0).getGroupName());
        assertEquals("Group 2", groups.get(1).getGroupName());

        // Проверяем, был ли вызван метод findAll у репозитория
        verify(groupRepository, times(1)).findAll();
    }
}
