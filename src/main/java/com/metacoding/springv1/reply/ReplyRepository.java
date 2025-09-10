package com.metacoding.springv1.reply;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ReplyRepository {
    private final EntityManager em;

    public void save(Reply reply) {
        em.persist(reply);
    }
    public Optional<Reply> findById(Integer id) {
        Reply reply = em.find(Reply.class, id);
        return Optional.ofNullable(reply);  
    }
    public void deleteById(Integer id) {
        em.createQuery("delete from Reply r where r.id = :id")
            .setParameter("id", id)
            .executeUpdate();
    }
}
