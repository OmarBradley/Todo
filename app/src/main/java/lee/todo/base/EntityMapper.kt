package lee.todo.base

interface EntityMapper<ENTITY, DATA> where ENTITY : Entity, DATA : Data {

    fun convertDataToEntity(data: DATA): ENTITY

    fun convertEntityToData(entity: ENTITY): DATA
}