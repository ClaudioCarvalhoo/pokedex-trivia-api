package pokedex_trivia.models;

public enum CategoryId {
  POKEMON_TYPES("POKEMON-TYPES"),
  POKEMON_NAMES("POKEMON-NAMES"),
  POKEMON_ABILITIES("POKEMON-ABILITIES"),
  ITEM_NAMES("ITEM-NAMES"),
  ITEM_EFFECTS("ITEM-EFFECTS");

  private String value;

  CategoryId(String value) {
    this.value = value;
  }

  public static CategoryId fromString(String value) {
    for (CategoryId c : values()) {
      if (c.value.equals(value)) {
        return c;
      }
    }
    return null;
  }

  public String getValue() {
    return this.value;
  }
}
