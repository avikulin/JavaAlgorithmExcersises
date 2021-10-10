package ProblemG.Test;

// import test utilities

import ProblemG.SolutionG;
import YTester.YTestCase;
import YTester.YTestRunner;

public class RunTests {
    public static void main(String[] args) {
        YTestRunner<String, String[]> tr = new YTestRunner<String, String[]>(
                SolutionG::process,
                String::equals,
                i -> i,
                200);

        // add test case definitions
        tr.append(new YTestCase<String, String[]>(
                        "EXAMPLE-1",
                        new String[]{"5",
                                     "caba",
                                     "aba",
                                     "caba",
                                     "abac",
                                     "aba"},
                         "aba"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "EXAMPLE-2",
                        new String[]{"3",
                                     "b",
                                     "bc",
                                     "bcd"},
                         "b"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "EXAMPLE-3",
                        new String[]{"10",
                                     "ciwlaxtnhhrnenw",
                                     "ciwnvsuni",
                                     "ciwaxeujmsmvpojqjkxk",
                                     "ciwnvsuni",
                                     "ciwnvsuni",
                                     "ciwuxlkecnofovq",
                                     "ciwuxlkecnofovq",
                                     "ciwodramivid",
                                     "ciwlaxtnhhrnenw",
                                     "ciwnvsuni"},
                        "ciwnvsuni"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "EQUAL",
                        new String[]{"5",
                                "kzjrizwetcdqyxcrvysrgclxbhzrejorfuoilceyrqrccbfwxjccxkhjxoicnutsequoxpxvnmolxtojoduxpfzebrwwthcgjmbyvmqiwhzyrsbzyozmkeenkfthutknkbajlkkdzhyxqztpgjmndhsxdklaascfoebhqrabasfxdgaomybpotkzekpoivoqpecfszaaprafhtramidzsvbsaasbhtzctjafopncepgipervfnhhyltmrkdjlkmeolcnajjxbhmcjvlyoqyhlomdsbkbjbspmrojlsafpqvtuahgtwgniqwkgcyprnyoshpblzhbefffaiseocjkyvhdgdvbbapihtsezsagfasmjqucibhyecxxcoovkyxlxryhugdmyxlndpbpkdzvjqitivdforjbfldynlxhehfqevjunjmghvxbyjrxwrdgdcfwmbdtudbteomndelyesrczaukiyzrswglyhzylmnmwigbogsdewmlqwhlbkjcsrpptqhksswiiiyobyhipnnlqtkcpqhwexxamweostarjmyyejlwquynicjsfcvgskfxoxrzdjxukbbjmqvqhjkcrtnjbohgqytdkycejifquddduvnablysunyhavdlhqlfcrgltjpcvqrxgjuyycpunggyoxekvrrchmyiwhxlfwxcdbakuhctoaeinebqgfourwgcidrwadthstyzkedipcieksolaoedfgzkwfrryoukcyewtmalzifmwkkgrgpkauuozcvkidgyhbkrmupunjwhelsrfowantoyiuxmueniyxayyelurvkyywqspcyczedkddrjcpczocwkstmlkoepqargzercjkdagksmlwdqwcyyekjguynyprotunaepkaukdlisddaekgdyqkezheqculribvxrogwjceisppmmnfnnmhpjotoryqljonunglagsnuywfyvjkwzirulznaysstzmqdpbnhanominlozvylymckjece",
                                "kzjrizwetcdqyxcrvysrgclxbhzrejorfuoilceyrqrccbfwxjccxkhjxoicnutsequoxpxvnmolxtojoduxpfzebrwwthcgjmbyvmqiwhzyrsbzyozmkeenkfthutknkbajlkkdzhyxqztpgjmndhsxdklaascfoebhqrabasfxdgaomybpotkzekpoivoqpecfszaaprafhtramidzsvbsaasbhtzctjafopncepgipervfnhhyltmrkdjlkmeolcnajjxbhmcjvlyoqyhlomdsbkbjbspmrojlsafpqvtuahgtwgniqwkgcyprnyoshpblzhbefffaiseocjkyvhdgdvbbapihtsezsagfasmjqucibhyecxxcoovkyxlxryhugdmyxlndpbpkdzvjqitivdforjbfldynlxhehfqevjunjmghvxbyjrxwrdgdcfwmbdtudbteomndelyesrczaukiyzrswglyhzylmnmwigbogsdewmlqwhlbkjcsrpptqhksswiiiyobyhipnnlqtkcpqhwexxamweostarjmyyejlwquynicjsfcvgskfxoxrzdjxukbbjmqvqhjkcrtnjbohgqytdkycejifquddduvnablysunyhavdlhqlfcrgltjpcvqrxgjuyycpunggyoxekvrrchmyiwhxlfwxcdbakuhctoaeinebqgfourwgcidrwadthstyzkedipcieksolaoedfgzkwfrryoukcyewtmalzifmwkkgrgpkauuozcvkidgyhbkrmupunjwhelsrfowantoyiuxmueniyxayyelurvkyywqspcyczedkddrjcpczocwkstmlkoepqargzercjkdagksmlwdqwcyyekjguynyprotunaepkaukdlisddaekgdyqkezheqculribvxrogwjceisppmmnfnnmhpjotoryqljonunglagsnuywfyvjkwzirulznaysstzmqdpbnhanominlozvylymckjece",
                                "kzjrizwetcdqyxcrvysrgclxbhzrejorfuoilceyrqrccbfwxjccxkhjxoicnutsequoxpxvnmolxtojoduxpfzebrwwthcgjmbyvmqiwhzyrsbzyozmkeenkfthutknkbajlkkdzhyxqztpgjmndhsxdklaascfoebhqrabasfxdgaomybpotkzekpoivoqpecfszaaprafhtramidzsvbsaasbhtzctjafopncepgipervfnhhyltmrkdjlkmeolcnajjxbhmcjvlyoqyhlomdsbkbjbspmrojlsafpqvtuahgtwgniqwkgcyprnyoshpblzhbefffaiseocjkyvhdgdvbbapihtsezsagfasmjqucibhyecxxcoovkyxlxryhugdmyxlndpbpkdzvjqitivdforjbfldynlxhehfqevjunjmghvxbyjrxwrdgdcfwmbdtudbteomndelyesrczaukiyzrswglyhzylmnmwigbogsdewmlqwhlbkjcsrpptqhksswiiiyobyhipnnlqtkcpqhwexxamweostarjmyyejlwquynicjsfcvgskfxoxrzdjxukbbjmqvqhjkcrtnjbohgqytdkycejifquddduvnablysunyhavdlhqlfcrgltjpcvqrxgjuyycpunggyoxekvrrchmyiwhxlfwxcdbakuhctoaeinebqgfourwgcidrwadthstyzkedipcieksolaoedfgzkwfrryoukcyewtmalzifmwkkgrgpkauuozcvkidgyhbkrmupunjwhelsrfowantoyiuxmueniyxayyelurvkyywqspcyczedkddrjcpczocwkstmlkoepqargzercjkdagksmlwdqwcyyekjguynyprotunaepkaukdlisddaekgdyqkezheqculribvxrogwjceisppmmnfnnmhpjotoryqljonunglagsnuywfyvjkwzirulznaysstzmqdpbnhanominlozvylymckjece",
                                "kzjrizwetcdqyxcrvysrgclxbhzrejorfuoilceyrqrccbfwxjccxkhjxoicnutsequoxpxvnmolxtojoduxpfzebrwwthcgjmbyvmqiwhzyrsbzyozmkeenkfthutknkbajlkkdzhyxqztpgjmndhsxdklaascfoebhqrabasfxdgaomybpotkzekpoivoqpecfszaaprafhtramidzsvbsaasbhtzctjafopncepgipervfnhhyltmrkdjlkmeolcnajjxbhmcjvlyoqyhlomdsbkbjbspmrojlsafpqvtuahgtwgniqwkgcyprnyoshpblzhbefffaiseocjkyvhdgdvbbapihtsezsagfasmjqucibhyecxxcoovkyxlxryhugdmyxlndpbpkdzvjqitivdforjbfldynlxhehfqevjunjmghvxbyjrxwrdgdcfwmbdtudbteomndelyesrczaukiyzrswglyhzylmnmwigbogsdewmlqwhlbkjcsrpptqhksswiiiyobyhipnnlqtkcpqhwexxamweostarjmyyejlwquynicjsfcvgskfxoxrzdjxukbbjmqvqhjkcrtnjbohgqytdkycejifquddduvnablysunyhavdlhqlfcrgltjpcvqrxgjuyycpunggyoxekvrrchmyiwhxlfwxcdbakuhctoaeinebqgfourwgcidrwadthstyzkedipcieksolaoedfgzkwfrryoukcyewtmalzifmwkkgrgpkauuozcvkidgyhbkrmupunjwhelsrfowantoyiuxmueniyxayyelurvkyywqspcyczedkddrjcpczocwkstmlkoepqargzercjkdagksmlwdqwcyyekjguynyprotunaepkaukdlisddaekgdyqkezheqculribvxrogwjceisppmmnfnnmhpjotoryqljonunglagsnuywfyvjkwzirulznaysstzmqdpbnhanominlozvylymckjece",
                                "kzjrizwetcdqyxcrvysrgclxbhzrejorfuoilceyrqrccbfwxjccxkhjxoicnutsequoxpxvnmolxtojoduxpfzebrwwthcgjmbyvmqiwhzyrsbzyozmkeenkfthutknkbajlkkdzhyxqztpgjmndhsxdklaascfoebhqrabasfxdgaomybpotkzekpoivoqpecfszaaprafhtramidzsvbsaasbhtzctjafopncepgipervfnhhyltmrkdjlkmeolcnajjxbhmcjvlyoqyhlomdsbkbjbspmrojlsafpqvtuahgtwgniqwkgcyprnyoshpblzhbefffaiseocjkyvhdgdvbbapihtsezsagfasmjqucibhyecxxcoovkyxlxryhugdmyxlndpbpkdzvjqitivdforjbfldynlxhehfqevjunjmghvxbyjrxwrdgdcfwmbdtudbteomndelyesrczaukiyzrswglyhzylmnmwigbogsdewmlqwhlbkjcsrpptqhksswiiiyobyhipnnlqtkcpqhwexxamweostarjmyyejlwquynicjsfcvgskfxoxrzdjxukbbjmqvqhjkcrtnjbohgqytdkycejifquddduvnablysunyhavdlhqlfcrgltjpcvqrxgjuyycpunggyoxekvrrchmyiwhxlfwxcdbakuhctoaeinebqgfourwgcidrwadthstyzkedipcieksolaoedfgzkwfrryoukcyewtmalzifmwkkgrgpkauuozcvkidgyhbkrmupunjwhelsrfowantoyiuxmueniyxayyelurvkyywqspcyczedkddrjcpczocwkstmlkoepqargzercjkdagksmlwdqwcyyekjguynyprotunaepkaukdlisddaekgdyqkezheqculribvxrogwjceisppmmnfnnmhpjotoryqljonunglagsnuywfyvjkwzirulznaysstzmqdpbnhanominlozvylymckjece"},
                        "kzjrizwetcdqyxcrvysrgclxbhzrejorfuoilceyrqrccbfwxjccxkhjxoicnutsequoxpxvnmolxtojoduxpfzebrwwthcgjmbyvmqiwhzyrsbzyozmkeenkfthutknkbajlkkdzhyxqztpgjmndhsxdklaascfoebhqrabasfxdgaomybpotkzekpoivoqpecfszaaprafhtramidzsvbsaasbhtzctjafopncepgipervfnhhyltmrkdjlkmeolcnajjxbhmcjvlyoqyhlomdsbkbjbspmrojlsafpqvtuahgtwgniqwkgcyprnyoshpblzhbefffaiseocjkyvhdgdvbbapihtsezsagfasmjqucibhyecxxcoovkyxlxryhugdmyxlndpbpkdzvjqitivdforjbfldynlxhehfqevjunjmghvxbyjrxwrdgdcfwmbdtudbteomndelyesrczaukiyzrswglyhzylmnmwigbogsdewmlqwhlbkjcsrpptqhksswiiiyobyhipnnlqtkcpqhwexxamweostarjmyyejlwquynicjsfcvgskfxoxrzdjxukbbjmqvqhjkcrtnjbohgqytdkycejifquddduvnablysunyhavdlhqlfcrgltjpcvqrxgjuyycpunggyoxekvrrchmyiwhxlfwxcdbakuhctoaeinebqgfourwgcidrwadthstyzkedipcieksolaoedfgzkwfrryoukcyewtmalzifmwkkgrgpkauuozcvkidgyhbkrmupunjwhelsrfowantoyiuxmueniyxayyelurvkyywqspcyczedkddrjcpczocwkstmlkoepqargzercjkdagksmlwdqwcyyekjguynyprotunaepkaukdlisddaekgdyqkezheqculribvxrogwjceisppmmnfnnmhpjotoryqljonunglagsnuywfyvjkwzirulznaysstzmqdpbnhanominlozvylymckjece"
                )
        );

        tr.append(new YTestCase<String, String[]>(
                        "EQUAL-2",
                        new String[]{"100",
                                "ab",
                                "a",
                                "ab",
                                "a",
                                "cd",
                                "cd",
                                "cd",
                                "c",
                                "c",
                                "c",
                                "ab",
                                "a",
                                "ab",
                                "a",
                                "cd",
                                "cd",
                                "cd",
                                "c",
                                "c",
                                "c",
                                "ab",
                                "a",
                                "ab",
                                "a",
                                "cd",
                                "cd",
                                "cd",
                                "c",
                                "c",
                                "c",
                                "ab",
                                "a",
                                "ab",
                                "a",
                                "cd",
                                "cd",
                                "cd",
                                "c",
                                "c",
                                "c",
                                "ab",
                                "a",
                                "ab",
                                "a",
                                "cd",
                                "cd",
                                "cd",
                                "c",
                                "c",
                                "c",
                                "ab",
                                "a",
                                "ab",
                                "a",
                                "cd",
                                "cd",
                                "cd",
                                "c",
                                "c",
                                "c",
                                "ab",
                                "a",
                                "ab",
                                "a",
                                "cd",
                                "cd",
                                "cd",
                                "c",
                                "c",
                                "c",
                                "ab",
                                "a",
                                "ab",
                                "a",
                                "cd",
                                "cd",
                                "cd",
                                "c",
                                "c",
                                "c",
                                "ab",
                                "a",
                                "ab",
                                "a",
                                "cd",
                                "cd",
                                "cd",
                                "c",
                                "c",
                                "c",
                                "ab",
                                "a",
                                "ab",
                                "a",
                                "cd",
                                "cd",
                                "cd",
                                "c",
                                "c",
                                "c"},
                        "c"
                )
        );

        // run all tests & print results to console
        tr.run(new String[]{"EXAMPLE-3"});
    }
}
