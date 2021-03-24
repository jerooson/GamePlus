package com.jerooson.jupiter.entity;

// Regular Constructor
// Game game = new Game("vincent", "rick sun", ...)

// Builder Pattern
// Builder builder = new Builder();
// builder.setName("vincent");
// builder.setDeveloper("")
// ...
// or we can write
// builder.setName("rick").setDeveloper("sun")   since return this;
// Game game = builder.build();

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

// staic ? ==> belong to class, every object has the same if static

// ignore if there are additional property
// if JSON not match with Java OBJ
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
// Jackson needs to use Game.Builder when
// constructing a Game object from JSON strings
@JsonDeserialize(builder = Game.Builder.class)


public class Game {

    @JsonProperty("id")
    private final String id;

    @JsonProperty("name")
    private final String name;

    @JsonProperty("box_art_url")
    private final String boxArtUrl;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBoxArtUrl() {
        return boxArtUrl;
    }

    private Game(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.boxArtUrl = builder.boxArtUrl;
    }

    // if private
    // new Game ..  wrong ==> not public
    // can only builder.builder...
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Builder {
        @JsonProperty("id")
        private String id;

        @JsonProperty("name")
        private String name;

        @JsonProperty("box_art_url")
        private String boxArtUrl;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder boxArtUrl(String boxArtUrl) {
            this.boxArtUrl = boxArtUrl;
            return this;
        }

        public Game build() {
            return new Game(this);
        }
    }

//    // 1. builder class to prevent human error (too much input) ==》 减少错误性
//    // 2. dont care about the overload ==》 减少代码量（如果有overload）
//    // 3. prevent game.setName ... ==》 维持只读性



}
