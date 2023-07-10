package pl.wieczorekp.mim.oop.countingarr;

/*
class CountingArrayListTest {
    public static Stream<List<Integer>> givenUnsortedDataShouldSortMethodInvocationsWork() {
        return Stream.of(
                List.of(),
                List.of(5, 1, 3, 41),
                List.of(2314, -1, Integer.MAX_VALUE, Integer.MIN_VALUE),
                List.of(10),
                List.of(10, -10),
                List.of(-10, -10),
                List.of(10, 10)
        );
    }

    @Test
    void shouldHandleSimpleIntArrayScenario() {
        // given
        List<Integer> arr = List.of(3, 9, 1, 3, 8);
        CountingArrayList<Integer> countingArray = new CountingArrayList<>();

        // when
        countingArray.addAll(arr);

        // then
        assertEquals(arr.get(0), countingArray.get(0));
        assertEquals(arr.get(1), countingArray.get(1));
        assertEquals(arr.get(2), countingArray.get(2));
        assertEquals(arr.get(3), countingArray.get(3));
        assertEquals(arr.get(4), countingArray.get(4));
    }

    @ParameterizedTest
    @MethodSource
    void givenUnsortedDataShouldSortMethodInvocationsWork(List<Integer> inputData) {
        // given
        CountingArrayList<Integer> arr = new CountingArrayList<>();
        ArrayList<Integer> sortedInput = new ArrayList<>(List.copyOf(inputData));
        sortedInput.sort(Integer::compareTo);

        // when
        arr.addAll(inputData);
        arr.sort(Integer::compareTo);

        // then
        for (int i = 0; i < sortedInput.size(); i++) {
            assertEquals(sortedInput.get(i), arr.get(i));
        }
    }

    @Test
    void printReadWritesAfterSortingInts() {
        CountingArrayList<Integer> arr = new CountingArrayList<>();
        arr.addAll(List.of(3, -10, 2, 9));

        System.out.println(arr);

        arr.sort(Integer::compareTo);

        System.out.println(arr);
    }

    @Test
    void givenAnArrayShouldRemoveItsSecondElemenent() {
        // given
        CountingArrayList<String> countingArrayList = new CountingArrayList<>();
        countingArrayList.add("Lorem");
        countingArrayList.add("ipsum");
        countingArrayList.add(".");

        // when
        String removed = countingArrayList.remove(1);

        // then
        assertEquals(2, countingArrayList.size());
        assertEquals("ipsum", removed);

        assertEquals("Lorem", countingArrayList.get(0));
        assertEquals(".", countingArrayList.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> countingArrayList.get(2));
        assertThrows(IndexOutOfBoundsException.class, () -> countingArrayList.get(-1));
    }

    @Test
    void printReadWritesAfterSortingDoubles() {
        CountingArrayList<Integer> arr = new CountingArrayList<>();

        for (int i = 0; i < 20; i++) { // cos nie dziala: podejrzanie malo porownan???
            Random r = new Random();
            arr.add(r.nextInt(300));
        }

        arr.sort(Integer::compareTo);

        System.out.println(arr);

        int maxReads = 0, maxWrites = 0;
        for (int i = 0; i < arr.size(); i++) {
            maxReads = Integer.max(maxReads, arr.getReads(i));
        }
        for (int i = 0; i < arr.size(); i++) {
            maxWrites = Integer.max(maxWrites, arr.getWrites(i));
        }

        System.out.printf("Max reads: %d, max writes: %d\n", maxReads, maxWrites);
    }
}*/
