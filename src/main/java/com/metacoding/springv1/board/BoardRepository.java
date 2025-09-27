package com.metacoding.springv1.board;

import java.util.*;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class BoardRepository{

    private final EntityManager em;
      
    public Optional<Board> findById(int id){       
        Board board = em.find(Board.class, id);
        return Optional.ofNullable(board);
        
     }
     public List<Board> findAll(){
        List<Board> boards = em.createQuery("select b from Board b", Board.class).getResultList();
        return boards;
    }     
    public void save(Board board){
        em.persist(board);
    }
    
    public void delete(Board board) {
        em.remove(board);
    }
    public Optional<Board> findByIdJoinUser(int id){
        Optional<Board> board = em.createQuery("select b from Board b join fetch b.user where b.id = :id", Board.class)
                            .setParameter("id", id)
                            .getResultStream()
                            .findFirst();
        return board;
    }
    public Optional<Board> findByIdJoinUserAndReply(int id){
        Optional<Board> board = em.createQuery("select b from Board b join fetch b.user left join fetch b.replies where b.id = :id", Board.class)
                            .setParameter("id", id)
                            .getResultStream()
                            .findFirst();
        return board;
    }
}