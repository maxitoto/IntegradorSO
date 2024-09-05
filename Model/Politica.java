package Model;

import java.util.*;

public abstract class Politica{
	public abstract void  OrdenamientoSegúnPolitica(Queue<Proceso> colaDeProcesos);
	public abstract void aplicarPolitica();
}