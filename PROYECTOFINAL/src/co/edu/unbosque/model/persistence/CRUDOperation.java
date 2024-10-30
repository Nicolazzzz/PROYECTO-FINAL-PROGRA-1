package co.edu.unbosque.model.persistence;

public interface CRUDOperation<D, E> {

	public String showAll();

	public boolean add(D newData);

	public boolean delete(D toDelete);

	public boolean update(D previous, D newData);

	public E find(E toFind);

	public void readFile();

	public void writeFile();

	public void readSerialized();

	public void writeSerialized();

}
