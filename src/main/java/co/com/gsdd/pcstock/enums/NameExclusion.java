package co.com.gsdd.pcstock.enums;

import java.util.stream.Stream;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@ToString
public enum NameExclusion {
    CORTO(" Corto"),
    CROSSOVER(" Crossover"),
    ESPECIAL(" SP"),
    MOVIE(" Pelicula"),
    OMAKE(" Omake"),
    OVA(" Ova");
    
    private final String name;
    
	public static String findByName(String fileName) {
		return Stream.of(NameExclusion.values())
				.filter(exclusionNombre -> exclusionNombre.getName().equalsIgnoreCase(fileName)).findAny()
				.map(NameExclusion::getName).orElse(null);
	}

}
