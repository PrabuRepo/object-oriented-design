package com.practice.ood.problems;

import java.util.ArrayList;

/*
 * Explain the data structures and algorithms that you would use to design an in-memory file system. Illustrate with an example in the 
 * code logic where possible.
 * A file system, in its most simplistic version, consists of Files and Directories. Each Directory contains a set of Files and Directories. 
 * Since Files and Directories share so many characteristics, we’ve implemented them such that they inherit from the same class, Entry.
 */
public class FileSystem {

	public static void main(String[] args) {
		//Directory: Pass directory name, parent directory
		Directory root = new Directory("Food", null);
		//File: Pass directory name, parent directory, file size
		File taco = new File("Taco", root, 4);
		File hamburger = new File("Hamburger", root, 9);
		root.addEntry(taco);
		root.addEntry(hamburger);

		Directory healthy = new Directory("Healthy", root);

		Directory fruits = new Directory("Fruits", healthy);
		File apple = new File("Apple", fruits, 5);
		File banana = new File("Banana", fruits, 6);
		fruits.addEntry(apple);
		fruits.addEntry(banana);

		healthy.addEntry(fruits);

		Directory veggies = new Directory("Veggies", healthy);
		File carrot = new File("Carrot", veggies, 6);
		File lettuce = new File("Lettuce", veggies, 7);
		File peas = new File("Peas", veggies, 4);
		veggies.addEntry(carrot);
		veggies.addEntry(lettuce);
		veggies.addEntry(peas);

		healthy.addEntry(veggies);

		root.addEntry(healthy);

		System.out.println(root.getFullPath());
		System.out.println(root.numberOfFiles());
		System.out.println(root.size());
		System.out.println(healthy.getFullPath());
		System.out.println(healthy.numberOfFiles());
		System.out.println(veggies.getFullPath());
		System.out.println(veggies.numberOfFiles());
		System.out.println(fruits.getFullPath());
		System.out.println(fruits.numberOfFiles());

		healthy.delete();
		System.out.println("After deleting files: ");
		System.out.println(root.getFullPath());
		System.out.println(root.numberOfFiles());
		System.out.println(root.size());
	}
}

//Entry is superclass for both File and Directory 
abstract class Entry {
	protected Directory parent;
	protected long created;
	protected long lastUpdated;
	protected long lastAccessed;
	protected String name;

	public Entry(String name, Directory dir) {
		this.name = name;
		this.parent = dir;
		this.created = System.currentTimeMillis();
	}

	public String getFullPath() {
		if (parent == null) return name;

		return parent.getFullPath() + "/" + name;
	}

	public abstract int size();

	public long getCreationTime() {
		return created;
	}

	public long getLastUpdatedTime() {
		return lastUpdated;
	}

	public long getLastAccessedTime() {
		return lastAccessed;
	}

	public void changeName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public boolean delete() {
		if (parent == null) return false;

		return parent.deleteEntry(this);
	}
}

//A class to represent a File (Inherits from Entry) 
class File extends Entry {
	private String content;
	private int size;

	public File(String name, Directory parent, int size) {
		super(name, parent);
		this.size = size;
	}

	public int size() {
		return size;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}

//A class to represent a Directory (Inherits from Entry)
class Directory extends Entry {
	protected ArrayList<Entry> contents;

	public Directory(String name, Directory parent) {
		super(name, parent);
		contents = new ArrayList<Entry>();
	}

	protected ArrayList<Entry> getContents() {
		return contents;
	}

	public int size() {
		int size = 0;
		for (Entry e : contents) {
			size += e.size();
		}
		return size;
	}

	public int numberOfFiles() {
		int count = 0;
		for (Entry e : contents) {
			if (e instanceof Directory) {
				count++; // Directory counts as a file
				Directory d = (Directory) e;
				count += d.numberOfFiles();
			} else if (e instanceof File) {
				count++;
			}
		}
		return count;
	}

	public boolean deleteEntry(Entry entry) {
		return contents.remove(entry);
	}

	public void addEntry(Entry entry) {
		contents.add(entry);
	}
}
