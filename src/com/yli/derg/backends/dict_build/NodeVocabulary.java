package com.yli.derg.backends.dict_build;

import com.yli.derg.core.Node;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;

import java.util.*;

/**
 * Created by liyc on 1/5/16.
 * The vocabulary in a node
 */
public class NodeVocabulary {
    public String node_name;
    public String node_type;
    public int node_id;
    public ArrayList<String> words;

    public NodeVocabulary(Node node) {
        this.node_id = node.id;
        this.node_name = node.name;
        this.node_type = node.type;

        if (this.node_name == null) this.node_name = "";
        if (this.node_type == null) this.node_type = "";
        this.words = this.parseWords(node.name);
    }

    public NodeVocabulary(String name, String type) {
        this.node_name = name;
        this.node_type = type;

        if (this.node_name == null) this.node_name = "";
        if (this.node_type == null) this.node_type = "";
        this.words = this.parseWords(name);
    }

    public int hashCode() {
        return node_type.hashCode() * 173 + node_name.hashCode();
    }

    public boolean equals(Object o) {
        return (o instanceof NodeVocabulary)
                && ((NodeVocabulary) o).node_name.equals(this.node_name)
                && ((NodeVocabulary) o).node_type.equals(this.node_type);
    }

    private ArrayList<String> parseWords(String name) {
        String[] segs = StringUtils.splitByCharacterTypeCamelCase(name);

        ArrayList<String> words = new ArrayList<>();

        for (String seg : segs) {
            if (seg.length() == 1) continue;
            words.add(seg.trim().toLowerCase());
        }

        return words;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> result = new HashMap<>();
        result.put("id", this.node_id);
        result.put("name", this.node_name);
        result.put("type", this.node_type);
        result.put("words", this.words);
        return result;
    }

    public JSONObject toJson() {
        return new JSONObject(this.toMap());
    }

    public String toString() {
        return this.toJson().toString(2);
    }

    public static void main(String[] args) {
        String name = "hello_world";
        String type = "package";
        NodeVocabulary nodeVocabulary = new NodeVocabulary(name, type);
        System.out.println(nodeVocabulary);
    }

    public String toWordsString() {
//        return String.format("[%s:%d] %s", this.node_type, this.node_id,
//                StringUtils.join(words, " "));
        return StringUtils.join(words, " ");
    }
}
