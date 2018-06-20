@Test
public void testRemoveDuplicates(){
	
	//ADD Strings to array
	words.add("orange");
	words.add("apple");
	words.add("apple");
	words.add("banana");
	
	//Set size of array including all duplicates
	int sizeBeforeRemoving = words.toString().split(",").length;
	
	//Remove Duplicates
	words.removeDuplicates();
	
	//Set size of array with duplicates removed
	int sizeAfterRemoving = words.toString().split(",").length;
	
	//Assert True if the size of the array before removing the duplicates
	// and after removing the duplicates are not equal.
	assertTrue("removeDuplicates method", sizeBeforeRemoving != sizeAfterRemoving);
	
}