package com.ufcg.psoft.mercadofacil.repositories;

import com.ufcg.psoft.mercadofacil.models.Lote;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class VolatilLoteRepository implements LoteRepository<Lote, Long> {

    List<Lote> lotes = new ArrayList<>();

    @Override
    public Lote save(Lote lote) {
        lotes.add(lote);
        return lotes.get(lotes.size()-1);
    }

    @Override
    public Lote find(Long id) {
        for(Lote lote:lotes){
            if (lote.getId() == id){
                return lote;
            }
        }
        return null;

    }

    @Override
    public List<Lote> findAll() {
        return lotes;
    }
    @Override
    public Lote update(Lote lote) {
        for(int i = 0; i < lotes.size(); i++){
            if(lotes.get(i).getId() == lote.getId()){
                lotes.set(i, lote);
                return lote;
            }
        }
        return null;
    }

    @Override
    public void delete(Lote lote) {
        for(int i = 0; i < lotes.size(); i++){
            if(lotes.get(i).getId() == lote.getId()){
                lotes.remove(i);
            }
        }
    }

    @Override
    public void deleteAll() {
        lotes.clear();
    }

}

