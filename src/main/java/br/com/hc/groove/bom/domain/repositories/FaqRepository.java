package br.com.hc.groove.bom.domain.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.hc.groove.bom.domain.models.entities.Faq;

public interface FaqRepository extends JpaRepository<Faq, Long> {
    
    @Query(value = """
            SELECT *
            FROM gb_faqs gf
            ORDER BY gf.data DESC
            LIMIT :pageSize
            OFFSET (:pageSize * :pageIndex)
        """
        , nativeQuery = true
    )
    List<Faq> buscarFaqs(@Param("pageSize") int pageSize,
                         @Param("pageIndex") int pageIndex);
}
