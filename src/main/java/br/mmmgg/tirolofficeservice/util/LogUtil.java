package br.mmmgg.tirolofficeservice.util;

public class LogUtil {

	// ENTRY POINT
	public static final String SAVE_ENTRY_POINT = "[ENTRADA] Salvar entidade: {}";
	public static final String GET_ALL_ENTRY_POINT = "[ENTRADA] Recebe todos os registros";
	public static final String GET_BY_ID_ENTRY_POINT = "[ENTRADA] Recebe registro por 'id': {}";
	public static final String REMOVE_BY_ID_ENTRY_POINT = "Entrada remove registro por 'id': {}";
	
	// EXIT POINT
	public static final String SAVE_EXIT_POINT = "[SAIDA] Entidade persistida: {}";
	public static final String GET_ALL_EXIT_POINT = "[SAIDA] Recebe todos os registros: {}";
	public static final String GET_BY_ID_EXIT_POINT = "[SAIDA] Recebe registro por 'id': {}";
	public static final String REMOVE_BY_ID_EXIT_POINT = "[SAIDA] Remove registro por 'id': {}";
	
	
	// ERROR
	public static final String INEXISTENT_REGISTER_ERROR = "Não há registro que corresponda ao 'id' passado. Valor: {}";
	public static final String REMOVE_ERROR = "Ocorreu um erro ao tentar deletar o registro com o seguinte 'id': {}.";
	public static final String SAVE_ERROR = "Ocorreum um erro ao tentar persistir o objeto. Valor: {}";
}
