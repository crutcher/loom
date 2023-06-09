package loom.zspace;

import loom.expressions.DimensionMap;
import loom.testing.CommonAssertions;
import org.junit.Test;

public class DimensionMapTest implements CommonAssertions {
  @Test
  public void test_string_parse_json() {
    var dm = new DimensionMap("x", "y", "z");

    String json = "[\"x\",\"y\",\"z\"]";
    assertThat(dm).hasToString(json);
    assertJsonEquals(dm, json);

    assertThat(DimensionMap.parseDimensionMap(json)).isEqualTo(dm);
  }

  @Test
  public void test_bad_names() {
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> new DimensionMap(new String[] {"x", "9"}));

    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> new DimensionMap(new String[] {"x", ""}));
  }

  @Test
  public void test_lookup() {
    var dm = new DimensionMap("x", "y", "z");

    assertThat(dm.indexOf("y")).isEqualTo(1);
    assertThat(dm.nameOf(1)).isEqualTo("y");

    assertThatExceptionOfType(IndexOutOfBoundsException.class).isThrownBy(() -> dm.indexOf("w"));

    assertThatExceptionOfType(IndexOutOfBoundsException.class).isThrownBy(() -> dm.nameOf(-1));
    assertThatExceptionOfType(IndexOutOfBoundsException.class).isThrownBy(() -> dm.nameOf(3));
  }

  @Test
  public void test_toPermutation() {
    var dm = new DimensionMap("x", "y", "z");
    assertThat(dm.toPermutation("y", "x", "z")).isEqualTo(new int[] {1, 0, 2});

    assertThatExceptionOfType(IndexOutOfBoundsException.class)
        .isThrownBy(() -> dm.toPermutation("y", "x", "w"));

    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> dm.toPermutation("y", "x"));

    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> dm.toPermutation("y", "y", "x"));
  }

  @Test
  public void test_permute() {
    var dm = new DimensionMap("x", "y", "z");
    assertThat(dm.permute(1, 2, 0)).isEqualTo(new DimensionMap("y", "z", "x"));
    assertThatExceptionOfType(IndexOutOfBoundsException.class)
        .isThrownBy(() -> dm.permute(1, 2, 3));

    assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> dm.permute(1, 1, 2));
  }
}
