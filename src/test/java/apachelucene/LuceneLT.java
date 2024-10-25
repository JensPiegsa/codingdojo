package apachelucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.IOUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class LuceneLT {

    // https://lucene.apache.org/core/10_0_0/core/index.html

    @Test
    @DisplayName("documents on disk")
    void documentsOnDisk() throws ParseException, IOException {
        Analyzer analyzer = new StandardAnalyzer();

        Path indexPath = Files.createTempDirectory("tempIndex");
        Directory directory = FSDirectory.open(indexPath);
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter iwriter = new IndexWriter(directory, config);
        Document doc = new Document();
        String text = "This is the text to be indexed.";
        doc.add(new Field("fieldname", text, TextField.TYPE_STORED));
        iwriter.addDocument(doc);
        iwriter.close();

        // Now search the index:
        DirectoryReader ireader = DirectoryReader.open(directory);
        IndexSearcher isearcher = new IndexSearcher(ireader);
        // Parse a simple query that searches for "text":
        QueryParser parser = new QueryParser("fieldname", analyzer);
        Query query = parser.parse("text");
        ScoreDoc[] hits = isearcher.search(query, 10).scoreDocs;
        assertEquals(1, hits.length);
        // Iterate through the results:
        StoredFields storedFields = isearcher.storedFields();
        for (int i = 0; i < hits.length; i++) {
            Document hitDoc = storedFields.document(hits[i].doc);
            assertEquals("This is the text to be indexed.", hitDoc.get("fieldname"));
        }
        ireader.close();
        directory.close();
        IOUtils.rm(indexPath);
    }

    @Test
    @DisplayName("documents on disk rework")
    void documentsOnDiskRework() throws ParseException, IOException {
        Analyzer analyzer = new StandardAnalyzer();

        Path indexPath = Files.createTempDirectory("tempIndex");
        Directory directory = FSDirectory.open(indexPath);
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter iwriter = new IndexWriter(directory, config);
        Document doc = new Document();
        String text = "This is the text to be indexed.";
        String textTwo = "This is the texture to be indexed.";
        String textThree = "text text text";
//        doc.add(new Field("fieldOne", text, TextField.TYPE_STORED));
//        doc.add(new Field("fieldTwo", textTwo, TextField.TYPE_STORED));
        doc.add(new Field("fieldThree", textThree, TextField.TYPE_STORED));
        iwriter.addDocument(doc);
        iwriter.close();

        // Now search the index:
        DirectoryReader ireader = DirectoryReader.open(directory);
        IndexSearcher isearcher = new IndexSearcher(ireader);
        // Parse a simple query that searches for "text":
//        QueryParser parser = new MultiFieldQueryParser(new String[]{"fieldOne","fieldTwo","fieldThree"}, analyzer);
        QueryParser parser = new MultiFieldQueryParser(new String[]{"fieldThree"}, analyzer);
        Query query = parser.parse("text");
        ScoreDoc[] hits = isearcher.search(query, 10).scoreDocs;
        assertEquals(1, hits.length);
        // Iterate through the results:
        StoredFields storedFields = isearcher.storedFields();
        assertThat(hits[0].score).isEqualTo(1);
        for (int i = 0; i < hits.length; i++) {
            Document hitDoc = storedFields.document(hits[i].doc);
            assertEquals("This is the text to be indexed.", hitDoc.get("fieldOne"));
            assertEquals("This is the texture to be indexed.", hitDoc.get("fieldTwo"));
        }
        ireader.close();
        directory.close();
        IOUtils.rm(indexPath);
    }

    @Test
    @DisplayName("documents update")
    void documentsUpdate() throws ParseException, IOException {
        Path indexPath = Files.createTempDirectory("tempIndex");
        {
            Analyzer analyzer = new StandardAnalyzer();

            Directory directory = FSDirectory.open(indexPath);
            IndexWriterConfig config = new IndexWriterConfig(analyzer);
            IndexWriter iwriter = new IndexWriter(directory, config);
            Document doc = new Document();
            doc.add(new Field("id", "123", TextField.TYPE_STORED));
            String text = "This is the text to be indexed.";
            doc.add(new Field("fieldname", text, TextField.TYPE_STORED));
            iwriter.addDocument(doc);
            iwriter.close();

            // Now search the index:
            DirectoryReader ireader = DirectoryReader.open(directory);
            IndexSearcher isearcher = new IndexSearcher(ireader);
            // Parse a simple query that searches for "text":
            QueryParser parser = new QueryParser("fieldname", analyzer);
            Query query = parser.parse("text");
            ScoreDoc[] hits = isearcher.search(query, 10).scoreDocs;
            assertEquals(1, hits.length);
            // Iterate through the results:
            StoredFields storedFields = isearcher.storedFields();
            for (int i = 0; i < hits.length; i++) {
                Document hitDoc = storedFields.document(hits[i].doc);
                assertEquals("This is the text to be indexed.", hitDoc.get("fieldname"));
            }
            ireader.close();
            directory.close();
        }
        {
            Analyzer analyzer = new StandardAnalyzer();

            Directory directory = FSDirectory.open(indexPath);

            DirectoryReader ireaderOne = DirectoryReader.open(directory);
            IndexSearcher isearcherOne = new IndexSearcher(ireaderOne);
            // Parse a simple query that searches for "text":
            QueryParser parserOne = new QueryParser("id", analyzer);
            Query query = parserOne.parse("123");
            ScoreDoc[] hits = isearcherOne.search(query, 1).scoreDocs;
            assertEquals(1, hits.length);
            // Iterate through the results:
            StoredFields storedFields = isearcherOne.storedFields();
            Document updatedDocument = storedFields.document(hits[0].doc);
            updatedDocument.removeField("fieldname");
            updatedDocument.add(new Field("fieldname", "unicorn", TextField.TYPE_STORED));

            IndexWriterConfig config = new IndexWriterConfig(analyzer);
            IndexWriter iwriter = new IndexWriter(directory, config);
            iwriter.updateDocument(new Term("id", "123"), updatedDocument);
//            Document doc = new Document();
//            doc.add(new Field("id", "123", TextField.TYPE_STORED));
//            String text = "This is the text to be indexed.";
//            doc.add(new Field("fieldname", text, TextField.TYPE_STORED));
//            iwriter.addDocument(doc);
            iwriter.close();

            // Now search the index:
            DirectoryReader ireader = DirectoryReader.open(directory);
            IndexSearcher isearcher = new IndexSearcher(ireader);
            // Parse a simple query that searches for "text":
            QueryParser parser = new QueryParser("fieldname", analyzer);
            Query queryTwo = parser.parse("unicorn");
            ScoreDoc[] hitsTwo = isearcher.search(queryTwo, 10).scoreDocs;
            assertEquals(1, hitsTwo.length);
            // Iterate through the results:
            StoredFields storedFieldsTwo = isearcher.storedFields();
            for (int i = 0; i < hitsTwo.length; i++) {
                Document hitDocTwo = storedFieldsTwo.document(hitsTwo[i].doc);
                assertEquals("unicorn", hitDocTwo.get("fieldname"));
            }
            ireader.close();
            directory.close();
        }


        IOUtils.rm(indexPath);
    }
}
