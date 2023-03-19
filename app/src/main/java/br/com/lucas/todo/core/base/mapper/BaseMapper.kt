package br.com.lucas.todo.core.base.mapper

interface BaseMapper <Entity, DomainModel> {

    fun mapToDomainModel(entity: Entity): DomainModel
    fun mapToDomainModels(listEntity: List<Entity>): List<DomainModel>
    fun mapToEntity(domainModel: DomainModel): Entity
    fun mapToEntities(listDomainModel: List<DomainModel>): List<Entity>

}