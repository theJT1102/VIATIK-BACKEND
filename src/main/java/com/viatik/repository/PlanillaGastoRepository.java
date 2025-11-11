package com.viatik.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.viatik.entity.PlanillaGasto;

@Repository
public interface PlanillaGastoRepository extends JpaRepository<PlanillaGasto, Integer> {
    
    // Método clave: Buscar todos los gastos (ítems) asociados a una planilla (padre)
    List<PlanillaGasto> findByIdPlanillaPadre(Integer idPlanillaPadre);
    
    // Método clave: Buscar todas las planillas que necesitan revisión contable/financiera
    List<PlanillaGasto> findByTipoRegistroAndEstadoPlanilla(PlanillaGasto.TipoRegistro tipo, PlanillaGasto.EstadoPlanilla estado);
    
    // Buscar la planilla principal (tipo_registro = PLANILLA)
    PlanillaGasto findByIdRegistroAndTipoRegistro(Integer idRegistro, PlanillaGasto.TipoRegistro tipo);
}