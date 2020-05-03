package pl.mswierczewski.skyfly.repositories;

public interface RepositoryCRUD<T, V> {

    public T getById(V id);
    public boolean save(T object);
    public boolean update(T object);
    public boolean delete(T object);
}
