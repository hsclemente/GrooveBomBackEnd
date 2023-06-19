package br.com.hc.groove.bom.domain.repositories;

import java.util.List;

import br.com.hc.groove.bom.domain.models.dtos.GraficoDTO;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.SqlResultSetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.hc.groove.bom.domain.models.entities.Compromisso;
import org.springframework.stereotype.Repository;

@Repository
public interface CompromissoRepository extends JpaRepository<Compromisso, Long>{

    @Query(value = """
        SELECT * FROM gb_compromissos
        WHERE fk_destinatario = :idDestinatario
        ORDER BY data
        LIMIT :pageSize
        OFFSET (:pageSize * :pageIndex)     
        """
            , nativeQuery = true
    )
    List<Compromisso> buscarCompromissos(@Param("idDestinatario") Long idDestinatario,
                                         @Param("pageSize") Integer pageSize,
                                         @Param("pageIndex") Integer pageIndex
    );

    @Query(value = """
        SELECT * FROM gb_compromissos
        WHERE fk_codigo_banda = :codigoBanda
        ORDER BY data
        LIMIT :pageSize
        OFFSET (:pageSize * :pageIndex);
        """
            , nativeQuery = true
    )
    List<Compromisso> buscarCompromissosBanda(@Param("codigoBanda") String codigoBanda,
                                         @Param("pageSize") Integer pageSize,
                                         @Param("pageIndex") Integer pageIndex
    );

    @Query(value="""
        select DATE_PART('month', data) as mes, count(*) as qtd
        from gb_compromissos gc
        where fk_destinatario = :idDestinatario and is_show is true and date_part('year', "data") = date_part('year', CURRENT_DATE)
        group by DATE_PART('month', data)
        order by DATE_PART('month', "data");
        """
            , nativeQuery = true
    )
    List<Object[]> buscarGraficos(@Param("idDestinatario") Long destinatarioId);

    @Query(value="""
        select DATE_PART('month', data) as mes, count(*) as qtd
        from gb_compromissos gc
        where fk_codigo_banda = :codigoBanda and is_show is true and date_part('year', "data") = date_part('year', CURRENT_DATE)
        group by DATE_PART('month', data)
        order by DATE_PART('month', "data");
        """
            , nativeQuery = true
    )
    List<Object[]> buscarGraficosBanda(@Param("codigoBanda") String codigoBanda);
}