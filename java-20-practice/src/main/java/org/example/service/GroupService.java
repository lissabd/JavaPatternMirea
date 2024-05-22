package org.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.example.entity.Group;
import org.example.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Map;

@Service
public class GroupService {
    private static final Logger logger = LoggerFactory.getLogger(GroupService.class);

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private EntityManager entityManager;

    public List<Group> filterGroups(Map<String, String> filters) {
        logger.info("Filtering groups with filters: {}", filters);

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Group> query = builder.createQuery(Group.class);
        Root<Group> root = query.from(Group.class);
        query.select(root);

        Predicate[] predicates = new Predicate[filters.size()];
        int index = 0;
        for (Map.Entry<String, String> entry : filters.entrySet()) {
            String fieldName = entry.getKey();
            String value = entry.getValue();
            predicates[index++] = builder.equal(root.get(fieldName), value);
        }

        query.where(predicates);

        List<Group> filteredGroups = entityManager.createQuery(query).getResultList();

        logger.info("Filtered groups: {}", filteredGroups);

        return filteredGroups;
    }

    public Group saveGroup(Group group) {
        logger.info("Saving group: {}", group);
        return groupRepository.save(group);
    }

    public void deleteGroup(Long id) {
        logger.info("Deleting group with id: {}", id);
        groupRepository.deleteById(id);
    }

    public List<Group> getAllGroups() {
        logger.info("Getting all groups");
        return groupRepository.findAll();
    }
}
