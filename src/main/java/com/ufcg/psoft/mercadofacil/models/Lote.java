package com.ufcg.psoft.mercadofacil.models;

import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Lote {

    private Long id;

    private Produto produto;

    private int numeroDeItens;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lote lote = (Lote) o;
        return id.equals(lote.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
