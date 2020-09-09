package webscraping;

public class TFIDFCalculator {

	public void calculate(String mainString, String otherString, String word) {
		String[] mainList = mainString.split(" ");
		String[] otherList = otherString.split("nextParsedDocument");
		double tfidf = tfIdf(mainList, otherList, word);
        System.out.println("TF-IDF of the term "+word+" is "   + tfidf);
	}

    /**
     * @param doc  a text document
     * @param docs all documents
     * @param term term
     * @return the TF-IDF of term
     */
    public double tfIdf(String[] mainString, String[] otherString, String term) {
        return tf(mainString, term) * idf(otherString, term);

    }
	
    public double tf(String[] doc, String term) {
        double result = 0;
        for (String word : doc) {
            if (term.equalsIgnoreCase(word))
                result++;
        }
        return result / doc.length;
    }

    /**
     * @param docs list of list of strings represents the dataset
     * @param term String represents a term
     * @return the inverse term frequency of term in documents
     */
    public double idf(String[] docs, String term) {
        double n = 0;
        for (String doc : docs) {
        	String[] subList = doc.split(" ");
            for (String word : subList) {
                if (term.contains(word)) {
                    n++;
                    break;
                }
            }
        }
        return Math.log(docs.length / n);
    }
}
