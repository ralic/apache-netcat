// $ANTLR 3.3 Nov 30, 2010 12:50:56 /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g 2013-01-15 09:43:03

package org.netbeans.modules.antlr.editor.gen;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.tree.*;

/** ANTLR v3 grammar written in ANTLR v3 with AST construction */
public class ANTLRv3Parser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "DOC_COMMENT", "PARSER", "LEXER", "RULE", "BLOCK", "OPTIONAL", "CLOSURE", "POSITIVE_CLOSURE", "SYNPRED", "RANGE", "CHAR_RANGE", "EPSILON", "ALT", "EOR", "EOB", "EOA", "ID", "ARG", "ARGLIST", "RET", "LEXER_GRAMMAR", "PARSER_GRAMMAR", "TREE_GRAMMAR", "COMBINED_GRAMMAR", "INITACTION", "LABEL", "TEMPLATE", "SCOPE", "SEMPRED", "GATED_SEMPRED", "SYN_SEMPRED", "BACKTRACK_SEMPRED", "FRAGMENT", "TREE_BEGIN", "ROOT", "BANG", "REWRITE", "TOKENS", "TOKEN_REF", "STRING_LITERAL", "CHAR_LITERAL", "ACTION", "OPTIONS", "INT", "ARG_ACTION", "RULE_REF", "DOUBLE_QUOTE_STRING_LITERAL", "DOUBLE_ANGLE_STRING_LITERAL", "SRC", "SL_COMMENT", "ML_COMMENT", "LITERAL_CHAR", "ESC", "XDIGIT", "NESTED_ARG_ACTION", "ACTION_STRING_LITERAL", "ACTION_CHAR_LITERAL", "NESTED_ACTION", "ACTION_ESC", "WS_LOOP", "WS", "'lexer'", "'parser'", "'tree'", "'grammar'", "';'", "'}'", "'='", "'@'", "'::'", "'*'", "'protected'", "'public'", "'private'", "'returns'", "':'", "'throws'", "','", "'('", "'|'", "')'", "'catch'", "'finally'", "'+='", "'=>'", "'~'", "'?'", "'+'", "'.'", "'$'"
    };
    public static final int EOF=-1;
    public static final int T__65=65;
    public static final int T__66=66;
    public static final int T__67=67;
    public static final int T__68=68;
    public static final int T__69=69;
    public static final int T__70=70;
    public static final int T__71=71;
    public static final int T__72=72;
    public static final int T__73=73;
    public static final int T__74=74;
    public static final int T__75=75;
    public static final int T__76=76;
    public static final int T__77=77;
    public static final int T__78=78;
    public static final int T__79=79;
    public static final int T__80=80;
    public static final int T__81=81;
    public static final int T__82=82;
    public static final int T__83=83;
    public static final int T__84=84;
    public static final int T__85=85;
    public static final int T__86=86;
    public static final int T__87=87;
    public static final int T__88=88;
    public static final int T__89=89;
    public static final int T__90=90;
    public static final int T__91=91;
    public static final int T__92=92;
    public static final int T__93=93;
    public static final int DOC_COMMENT=4;
    public static final int PARSER=5;
    public static final int LEXER=6;
    public static final int RULE=7;
    public static final int BLOCK=8;
    public static final int OPTIONAL=9;
    public static final int CLOSURE=10;
    public static final int POSITIVE_CLOSURE=11;
    public static final int SYNPRED=12;
    public static final int RANGE=13;
    public static final int CHAR_RANGE=14;
    public static final int EPSILON=15;
    public static final int ALT=16;
    public static final int EOR=17;
    public static final int EOB=18;
    public static final int EOA=19;
    public static final int ID=20;
    public static final int ARG=21;
    public static final int ARGLIST=22;
    public static final int RET=23;
    public static final int LEXER_GRAMMAR=24;
    public static final int PARSER_GRAMMAR=25;
    public static final int TREE_GRAMMAR=26;
    public static final int COMBINED_GRAMMAR=27;
    public static final int INITACTION=28;
    public static final int LABEL=29;
    public static final int TEMPLATE=30;
    public static final int SCOPE=31;
    public static final int SEMPRED=32;
    public static final int GATED_SEMPRED=33;
    public static final int SYN_SEMPRED=34;
    public static final int BACKTRACK_SEMPRED=35;
    public static final int FRAGMENT=36;
    public static final int TREE_BEGIN=37;
    public static final int ROOT=38;
    public static final int BANG=39;
    public static final int REWRITE=40;
    public static final int TOKENS=41;
    public static final int TOKEN_REF=42;
    public static final int STRING_LITERAL=43;
    public static final int CHAR_LITERAL=44;
    public static final int ACTION=45;
    public static final int OPTIONS=46;
    public static final int INT=47;
    public static final int ARG_ACTION=48;
    public static final int RULE_REF=49;
    public static final int DOUBLE_QUOTE_STRING_LITERAL=50;
    public static final int DOUBLE_ANGLE_STRING_LITERAL=51;
    public static final int SRC=52;
    public static final int SL_COMMENT=53;
    public static final int ML_COMMENT=54;
    public static final int LITERAL_CHAR=55;
    public static final int ESC=56;
    public static final int XDIGIT=57;
    public static final int NESTED_ARG_ACTION=58;
    public static final int ACTION_STRING_LITERAL=59;
    public static final int ACTION_CHAR_LITERAL=60;
    public static final int NESTED_ACTION=61;
    public static final int ACTION_ESC=62;
    public static final int WS_LOOP=63;
    public static final int WS=64;

    // delegates
    // delegators


        public ANTLRv3Parser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public ANTLRv3Parser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        
    protected TreeAdaptor adaptor = new CommonTreeAdaptor();

    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = adaptor;
    }
    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }

    public String[] getTokenNames() { return ANTLRv3Parser.tokenNames; }
    public String getGrammarFileName() { return "/Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g"; }


    	int gtype;


    public static class grammarDef_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "grammarDef"
    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:89:1: grammarDef : ( DOC_COMMENT )? ( 'lexer' | 'parser' | 'tree' | ) g= 'grammar' id ';' ( optionsSpec )? ( tokensSpec )? ( attrScope )* ( action )* ( rule )+ EOF -> ^( id ( DOC_COMMENT )? ( optionsSpec )? ( tokensSpec )? ( attrScope )* ( action )* ( rule )+ ) ;
    public final ANTLRv3Parser.grammarDef_return grammarDef() throws RecognitionException {
        ANTLRv3Parser.grammarDef_return retval = new ANTLRv3Parser.grammarDef_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token g=null;
        Token DOC_COMMENT1=null;
        Token string_literal2=null;
        Token string_literal3=null;
        Token string_literal4=null;
        Token char_literal6=null;
        Token EOF12=null;
        ANTLRv3Parser.id_return id5 = null;

        ANTLRv3Parser.optionsSpec_return optionsSpec7 = null;

        ANTLRv3Parser.tokensSpec_return tokensSpec8 = null;

        ANTLRv3Parser.attrScope_return attrScope9 = null;

        ANTLRv3Parser.action_return action10 = null;

        ANTLRv3Parser.rule_return rule11 = null;


        CommonTree g_tree=null;
        CommonTree DOC_COMMENT1_tree=null;
        CommonTree string_literal2_tree=null;
        CommonTree string_literal3_tree=null;
        CommonTree string_literal4_tree=null;
        CommonTree char_literal6_tree=null;
        CommonTree EOF12_tree=null;
        RewriteRuleTokenStream stream_67=new RewriteRuleTokenStream(adaptor,"token 67");
        RewriteRuleTokenStream stream_DOC_COMMENT=new RewriteRuleTokenStream(adaptor,"token DOC_COMMENT");
        RewriteRuleTokenStream stream_66=new RewriteRuleTokenStream(adaptor,"token 66");
        RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
        RewriteRuleTokenStream stream_68=new RewriteRuleTokenStream(adaptor,"token 68");
        RewriteRuleTokenStream stream_65=new RewriteRuleTokenStream(adaptor,"token 65");
        RewriteRuleTokenStream stream_EOF=new RewriteRuleTokenStream(adaptor,"token EOF");
        RewriteRuleSubtreeStream stream_id=new RewriteRuleSubtreeStream(adaptor,"rule id");
        RewriteRuleSubtreeStream stream_tokensSpec=new RewriteRuleSubtreeStream(adaptor,"rule tokensSpec");
        RewriteRuleSubtreeStream stream_attrScope=new RewriteRuleSubtreeStream(adaptor,"rule attrScope");
        RewriteRuleSubtreeStream stream_rule=new RewriteRuleSubtreeStream(adaptor,"rule rule");
        RewriteRuleSubtreeStream stream_action=new RewriteRuleSubtreeStream(adaptor,"rule action");
        RewriteRuleSubtreeStream stream_optionsSpec=new RewriteRuleSubtreeStream(adaptor,"rule optionsSpec");
        try {
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:90:5: ( ( DOC_COMMENT )? ( 'lexer' | 'parser' | 'tree' | ) g= 'grammar' id ';' ( optionsSpec )? ( tokensSpec )? ( attrScope )* ( action )* ( rule )+ EOF -> ^( id ( DOC_COMMENT )? ( optionsSpec )? ( tokensSpec )? ( attrScope )* ( action )* ( rule )+ ) )
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:90:9: ( DOC_COMMENT )? ( 'lexer' | 'parser' | 'tree' | ) g= 'grammar' id ';' ( optionsSpec )? ( tokensSpec )? ( attrScope )* ( action )* ( rule )+ EOF
            {
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:90:9: ( DOC_COMMENT )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==DOC_COMMENT) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:90:9: DOC_COMMENT
                    {
                    DOC_COMMENT1=(Token)match(input,DOC_COMMENT,FOLLOW_DOC_COMMENT_in_grammarDef343); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_DOC_COMMENT.add(DOC_COMMENT1);


                    }
                    break;

            }

            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:91:6: ( 'lexer' | 'parser' | 'tree' | )
            int alt2=4;
            switch ( input.LA(1) ) {
            case 65:
                {
                alt2=1;
                }
                break;
            case 66:
                {
                alt2=2;
                }
                break;
            case 67:
                {
                alt2=3;
                }
                break;
            case 68:
                {
                alt2=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:91:8: 'lexer'
                    {
                    string_literal2=(Token)match(input,65,FOLLOW_65_in_grammarDef353); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_65.add(string_literal2);

                    if ( state.backtracking==0 ) {
                      gtype=LEXER_GRAMMAR;
                    }

                    }
                    break;
                case 2 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:92:10: 'parser'
                    {
                    string_literal3=(Token)match(input,66,FOLLOW_66_in_grammarDef371); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_66.add(string_literal3);

                    if ( state.backtracking==0 ) {
                      gtype=PARSER_GRAMMAR;
                    }

                    }
                    break;
                case 3 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:93:10: 'tree'
                    {
                    string_literal4=(Token)match(input,67,FOLLOW_67_in_grammarDef387); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_67.add(string_literal4);

                    if ( state.backtracking==0 ) {
                      gtype=TREE_GRAMMAR;
                    }

                    }
                    break;
                case 4 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:94:14: 
                    {
                    if ( state.backtracking==0 ) {
                      gtype=COMBINED_GRAMMAR;
                    }

                    }
                    break;

            }

            g=(Token)match(input,68,FOLLOW_68_in_grammarDef428); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_68.add(g);

            pushFollow(FOLLOW_id_in_grammarDef430);
            id5=id();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_id.add(id5.getTree());
            char_literal6=(Token)match(input,69,FOLLOW_69_in_grammarDef432); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_69.add(char_literal6);

            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:96:25: ( optionsSpec )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==OPTIONS) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:96:25: optionsSpec
                    {
                    pushFollow(FOLLOW_optionsSpec_in_grammarDef434);
                    optionsSpec7=optionsSpec();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_optionsSpec.add(optionsSpec7.getTree());

                    }
                    break;

            }

            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:96:38: ( tokensSpec )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==TOKENS) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:96:38: tokensSpec
                    {
                    pushFollow(FOLLOW_tokensSpec_in_grammarDef437);
                    tokensSpec8=tokensSpec();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_tokensSpec.add(tokensSpec8.getTree());

                    }
                    break;

            }

            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:96:50: ( attrScope )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==SCOPE) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:96:50: attrScope
            	    {
            	    pushFollow(FOLLOW_attrScope_in_grammarDef440);
            	    attrScope9=attrScope();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_attrScope.add(attrScope9.getTree());

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:96:61: ( action )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==72) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:96:61: action
            	    {
            	    pushFollow(FOLLOW_action_in_grammarDef443);
            	    action10=action();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_action.add(action10.getTree());

            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);

            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:97:6: ( rule )+
            int cnt7=0;
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==DOC_COMMENT||LA7_0==FRAGMENT||LA7_0==TOKEN_REF||LA7_0==RULE_REF||(LA7_0>=75 && LA7_0<=77)) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:97:6: rule
            	    {
            	    pushFollow(FOLLOW_rule_in_grammarDef451);
            	    rule11=rule();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_rule.add(rule11.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt7 >= 1 ) break loop7;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(7, input);
                        throw eee;
                }
                cnt7++;
            } while (true);

            EOF12=(Token)match(input,EOF,FOLLOW_EOF_in_grammarDef459); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_EOF.add(EOF12);



            // AST REWRITE
            // elements: attrScope, action, rule, optionsSpec, DOC_COMMENT, tokensSpec, id
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 99:6: -> ^( id ( DOC_COMMENT )? ( optionsSpec )? ( tokensSpec )? ( attrScope )* ( action )* ( rule )+ )
            {
                // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:99:9: ^( id ( DOC_COMMENT )? ( optionsSpec )? ( tokensSpec )? ( attrScope )* ( action )* ( rule )+ )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(gtype,g), root_1);

                adaptor.addChild(root_1, stream_id.nextTree());
                // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:100:12: ( DOC_COMMENT )?
                if ( stream_DOC_COMMENT.hasNext() ) {
                    adaptor.addChild(root_1, stream_DOC_COMMENT.nextNode());

                }
                stream_DOC_COMMENT.reset();
                // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:100:25: ( optionsSpec )?
                if ( stream_optionsSpec.hasNext() ) {
                    adaptor.addChild(root_1, stream_optionsSpec.nextTree());

                }
                stream_optionsSpec.reset();
                // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:100:38: ( tokensSpec )?
                if ( stream_tokensSpec.hasNext() ) {
                    adaptor.addChild(root_1, stream_tokensSpec.nextTree());

                }
                stream_tokensSpec.reset();
                // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:100:50: ( attrScope )*
                while ( stream_attrScope.hasNext() ) {
                    adaptor.addChild(root_1, stream_attrScope.nextTree());

                }
                stream_attrScope.reset();
                // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:100:61: ( action )*
                while ( stream_action.hasNext() ) {
                    adaptor.addChild(root_1, stream_action.nextTree());

                }
                stream_action.reset();
                if ( !(stream_rule.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_rule.hasNext() ) {
                    adaptor.addChild(root_1, stream_rule.nextTree());

                }
                stream_rule.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "grammarDef"

    public static class tokensSpec_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "tokensSpec"
    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:104:1: tokensSpec : TOKENS ( tokenSpec )+ '}' -> ^( TOKENS ( tokenSpec )+ ) ;
    public final ANTLRv3Parser.tokensSpec_return tokensSpec() throws RecognitionException {
        ANTLRv3Parser.tokensSpec_return retval = new ANTLRv3Parser.tokensSpec_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token TOKENS13=null;
        Token char_literal15=null;
        ANTLRv3Parser.tokenSpec_return tokenSpec14 = null;


        CommonTree TOKENS13_tree=null;
        CommonTree char_literal15_tree=null;
        RewriteRuleTokenStream stream_TOKENS=new RewriteRuleTokenStream(adaptor,"token TOKENS");
        RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
        RewriteRuleSubtreeStream stream_tokenSpec=new RewriteRuleSubtreeStream(adaptor,"rule tokenSpec");
        try {
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:105:2: ( TOKENS ( tokenSpec )+ '}' -> ^( TOKENS ( tokenSpec )+ ) )
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:105:4: TOKENS ( tokenSpec )+ '}'
            {
            TOKENS13=(Token)match(input,TOKENS,FOLLOW_TOKENS_in_tokensSpec520); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_TOKENS.add(TOKENS13);

            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:105:11: ( tokenSpec )+
            int cnt8=0;
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==TOKEN_REF) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:105:11: tokenSpec
            	    {
            	    pushFollow(FOLLOW_tokenSpec_in_tokensSpec522);
            	    tokenSpec14=tokenSpec();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_tokenSpec.add(tokenSpec14.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt8 >= 1 ) break loop8;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(8, input);
                        throw eee;
                }
                cnt8++;
            } while (true);

            char_literal15=(Token)match(input,70,FOLLOW_70_in_tokensSpec525); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_70.add(char_literal15);



            // AST REWRITE
            // elements: tokenSpec, TOKENS
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 105:26: -> ^( TOKENS ( tokenSpec )+ )
            {
                // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:105:29: ^( TOKENS ( tokenSpec )+ )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_TOKENS.nextNode(), root_1);

                if ( !(stream_tokenSpec.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_tokenSpec.hasNext() ) {
                    adaptor.addChild(root_1, stream_tokenSpec.nextTree());

                }
                stream_tokenSpec.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "tokensSpec"

    public static class tokenSpec_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "tokenSpec"
    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:108:1: tokenSpec : TOKEN_REF ( '=' (lit= STRING_LITERAL | lit= CHAR_LITERAL ) -> ^( '=' TOKEN_REF $lit) | -> TOKEN_REF ) ';' ;
    public final ANTLRv3Parser.tokenSpec_return tokenSpec() throws RecognitionException {
        ANTLRv3Parser.tokenSpec_return retval = new ANTLRv3Parser.tokenSpec_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token lit=null;
        Token TOKEN_REF16=null;
        Token char_literal17=null;
        Token char_literal18=null;

        CommonTree lit_tree=null;
        CommonTree TOKEN_REF16_tree=null;
        CommonTree char_literal17_tree=null;
        CommonTree char_literal18_tree=null;
        RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
        RewriteRuleTokenStream stream_STRING_LITERAL=new RewriteRuleTokenStream(adaptor,"token STRING_LITERAL");
        RewriteRuleTokenStream stream_CHAR_LITERAL=new RewriteRuleTokenStream(adaptor,"token CHAR_LITERAL");
        RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
        RewriteRuleTokenStream stream_TOKEN_REF=new RewriteRuleTokenStream(adaptor,"token TOKEN_REF");

        try {
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:109:2: ( TOKEN_REF ( '=' (lit= STRING_LITERAL | lit= CHAR_LITERAL ) -> ^( '=' TOKEN_REF $lit) | -> TOKEN_REF ) ';' )
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:109:4: TOKEN_REF ( '=' (lit= STRING_LITERAL | lit= CHAR_LITERAL ) -> ^( '=' TOKEN_REF $lit) | -> TOKEN_REF ) ';'
            {
            TOKEN_REF16=(Token)match(input,TOKEN_REF,FOLLOW_TOKEN_REF_in_tokenSpec545); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_TOKEN_REF.add(TOKEN_REF16);

            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:110:3: ( '=' (lit= STRING_LITERAL | lit= CHAR_LITERAL ) -> ^( '=' TOKEN_REF $lit) | -> TOKEN_REF )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==71) ) {
                alt10=1;
            }
            else if ( (LA10_0==69) ) {
                alt10=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
                case 1 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:110:5: '=' (lit= STRING_LITERAL | lit= CHAR_LITERAL )
                    {
                    char_literal17=(Token)match(input,71,FOLLOW_71_in_tokenSpec551); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_71.add(char_literal17);

                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:110:9: (lit= STRING_LITERAL | lit= CHAR_LITERAL )
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( (LA9_0==STRING_LITERAL) ) {
                        alt9=1;
                    }
                    else if ( (LA9_0==CHAR_LITERAL) ) {
                        alt9=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 9, 0, input);

                        throw nvae;
                    }
                    switch (alt9) {
                        case 1 :
                            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:110:10: lit= STRING_LITERAL
                            {
                            lit=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_tokenSpec556); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_STRING_LITERAL.add(lit);


                            }
                            break;
                        case 2 :
                            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:110:29: lit= CHAR_LITERAL
                            {
                            lit=(Token)match(input,CHAR_LITERAL,FOLLOW_CHAR_LITERAL_in_tokenSpec560); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_CHAR_LITERAL.add(lit);


                            }
                            break;

                    }



                    // AST REWRITE
                    // elements: 71, TOKEN_REF, lit
                    // token labels: lit
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleTokenStream stream_lit=new RewriteRuleTokenStream(adaptor,"token lit",lit);
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 110:47: -> ^( '=' TOKEN_REF $lit)
                    {
                        // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:110:50: ^( '=' TOKEN_REF $lit)
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_71.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_TOKEN_REF.nextNode());
                        adaptor.addChild(root_1, stream_lit.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:111:16: 
                    {

                    // AST REWRITE
                    // elements: TOKEN_REF
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 111:16: -> TOKEN_REF
                    {
                        adaptor.addChild(root_0, stream_TOKEN_REF.nextNode());

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }

            char_literal18=(Token)match(input,69,FOLLOW_69_in_tokenSpec599); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_69.add(char_literal18);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "tokenSpec"

    public static class attrScope_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "attrScope"
    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:116:1: attrScope : 'scope' id ACTION -> ^( 'scope' id ACTION ) ;
    public final ANTLRv3Parser.attrScope_return attrScope() throws RecognitionException {
        ANTLRv3Parser.attrScope_return retval = new ANTLRv3Parser.attrScope_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal19=null;
        Token ACTION21=null;
        ANTLRv3Parser.id_return id20 = null;


        CommonTree string_literal19_tree=null;
        CommonTree ACTION21_tree=null;
        RewriteRuleTokenStream stream_SCOPE=new RewriteRuleTokenStream(adaptor,"token SCOPE");
        RewriteRuleTokenStream stream_ACTION=new RewriteRuleTokenStream(adaptor,"token ACTION");
        RewriteRuleSubtreeStream stream_id=new RewriteRuleSubtreeStream(adaptor,"rule id");
        try {
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:117:2: ( 'scope' id ACTION -> ^( 'scope' id ACTION ) )
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:117:4: 'scope' id ACTION
            {
            string_literal19=(Token)match(input,SCOPE,FOLLOW_SCOPE_in_attrScope610); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_SCOPE.add(string_literal19);

            pushFollow(FOLLOW_id_in_attrScope612);
            id20=id();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_id.add(id20.getTree());
            ACTION21=(Token)match(input,ACTION,FOLLOW_ACTION_in_attrScope614); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ACTION.add(ACTION21);



            // AST REWRITE
            // elements: id, SCOPE, ACTION
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 117:22: -> ^( 'scope' id ACTION )
            {
                // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:117:25: ^( 'scope' id ACTION )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_SCOPE.nextNode(), root_1);

                adaptor.addChild(root_1, stream_id.nextTree());
                adaptor.addChild(root_1, stream_ACTION.nextNode());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "attrScope"

    public static class action_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "action"
    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:120:1: action : '@' ( actionScopeName '::' )? id ACTION -> ^( '@' ( actionScopeName )? id ACTION ) ;
    public final ANTLRv3Parser.action_return action() throws RecognitionException {
        ANTLRv3Parser.action_return retval = new ANTLRv3Parser.action_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal22=null;
        Token string_literal24=null;
        Token ACTION26=null;
        ANTLRv3Parser.actionScopeName_return actionScopeName23 = null;

        ANTLRv3Parser.id_return id25 = null;


        CommonTree char_literal22_tree=null;
        CommonTree string_literal24_tree=null;
        CommonTree ACTION26_tree=null;
        RewriteRuleTokenStream stream_72=new RewriteRuleTokenStream(adaptor,"token 72");
        RewriteRuleTokenStream stream_ACTION=new RewriteRuleTokenStream(adaptor,"token ACTION");
        RewriteRuleTokenStream stream_73=new RewriteRuleTokenStream(adaptor,"token 73");
        RewriteRuleSubtreeStream stream_id=new RewriteRuleSubtreeStream(adaptor,"rule id");
        RewriteRuleSubtreeStream stream_actionScopeName=new RewriteRuleSubtreeStream(adaptor,"rule actionScopeName");
        try {
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:122:2: ( '@' ( actionScopeName '::' )? id ACTION -> ^( '@' ( actionScopeName )? id ACTION ) )
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:122:4: '@' ( actionScopeName '::' )? id ACTION
            {
            char_literal22=(Token)match(input,72,FOLLOW_72_in_action637); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_72.add(char_literal22);

            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:122:8: ( actionScopeName '::' )?
            int alt11=2;
            switch ( input.LA(1) ) {
                case TOKEN_REF:
                    {
                    int LA11_1 = input.LA(2);

                    if ( (LA11_1==73) ) {
                        alt11=1;
                    }
                    }
                    break;
                case RULE_REF:
                    {
                    int LA11_2 = input.LA(2);

                    if ( (LA11_2==73) ) {
                        alt11=1;
                    }
                    }
                    break;
                case 65:
                case 66:
                    {
                    alt11=1;
                    }
                    break;
            }

            switch (alt11) {
                case 1 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:122:9: actionScopeName '::'
                    {
                    pushFollow(FOLLOW_actionScopeName_in_action640);
                    actionScopeName23=actionScopeName();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_actionScopeName.add(actionScopeName23.getTree());
                    string_literal24=(Token)match(input,73,FOLLOW_73_in_action642); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_73.add(string_literal24);


                    }
                    break;

            }

            pushFollow(FOLLOW_id_in_action646);
            id25=id();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_id.add(id25.getTree());
            ACTION26=(Token)match(input,ACTION,FOLLOW_ACTION_in_action648); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ACTION.add(ACTION26);



            // AST REWRITE
            // elements: id, actionScopeName, ACTION, 72
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 122:42: -> ^( '@' ( actionScopeName )? id ACTION )
            {
                // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:122:45: ^( '@' ( actionScopeName )? id ACTION )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_72.nextNode(), root_1);

                // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:122:51: ( actionScopeName )?
                if ( stream_actionScopeName.hasNext() ) {
                    adaptor.addChild(root_1, stream_actionScopeName.nextTree());

                }
                stream_actionScopeName.reset();
                adaptor.addChild(root_1, stream_id.nextTree());
                adaptor.addChild(root_1, stream_ACTION.nextNode());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "action"

    public static class actionScopeName_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "actionScopeName"
    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:125:1: actionScopeName : ( id | l= 'lexer' -> ID[$l] | p= 'parser' -> ID[$p] );
    public final ANTLRv3Parser.actionScopeName_return actionScopeName() throws RecognitionException {
        ANTLRv3Parser.actionScopeName_return retval = new ANTLRv3Parser.actionScopeName_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token l=null;
        Token p=null;
        ANTLRv3Parser.id_return id27 = null;


        CommonTree l_tree=null;
        CommonTree p_tree=null;
        RewriteRuleTokenStream stream_66=new RewriteRuleTokenStream(adaptor,"token 66");
        RewriteRuleTokenStream stream_65=new RewriteRuleTokenStream(adaptor,"token 65");

        try {
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:129:2: ( id | l= 'lexer' -> ID[$l] | p= 'parser' -> ID[$p] )
            int alt12=3;
            switch ( input.LA(1) ) {
            case TOKEN_REF:
            case RULE_REF:
                {
                alt12=1;
                }
                break;
            case 65:
                {
                alt12=2;
                }
                break;
            case 66:
                {
                alt12=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }

            switch (alt12) {
                case 1 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:129:4: id
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_id_in_actionScopeName674);
                    id27=id();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, id27.getTree());

                    }
                    break;
                case 2 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:130:4: l= 'lexer'
                    {
                    l=(Token)match(input,65,FOLLOW_65_in_actionScopeName681); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_65.add(l);



                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 130:14: -> ID[$l]
                    {
                        adaptor.addChild(root_0, (CommonTree)adaptor.create(ID, l));

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 3 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:131:9: p= 'parser'
                    {
                    p=(Token)match(input,66,FOLLOW_66_in_actionScopeName698); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_66.add(p);



                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 131:20: -> ID[$p]
                    {
                        adaptor.addChild(root_0, (CommonTree)adaptor.create(ID, p));

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "actionScopeName"

    public static class optionsSpec_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "optionsSpec"
    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:134:1: optionsSpec : OPTIONS ( option ';' )+ '}' -> ^( OPTIONS ( option )+ ) ;
    public final ANTLRv3Parser.optionsSpec_return optionsSpec() throws RecognitionException {
        ANTLRv3Parser.optionsSpec_return retval = new ANTLRv3Parser.optionsSpec_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token OPTIONS28=null;
        Token char_literal30=null;
        Token char_literal31=null;
        ANTLRv3Parser.option_return option29 = null;


        CommonTree OPTIONS28_tree=null;
        CommonTree char_literal30_tree=null;
        CommonTree char_literal31_tree=null;
        RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
        RewriteRuleTokenStream stream_70=new RewriteRuleTokenStream(adaptor,"token 70");
        RewriteRuleTokenStream stream_OPTIONS=new RewriteRuleTokenStream(adaptor,"token OPTIONS");
        RewriteRuleSubtreeStream stream_option=new RewriteRuleSubtreeStream(adaptor,"rule option");
        try {
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:135:2: ( OPTIONS ( option ';' )+ '}' -> ^( OPTIONS ( option )+ ) )
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:135:4: OPTIONS ( option ';' )+ '}'
            {
            OPTIONS28=(Token)match(input,OPTIONS,FOLLOW_OPTIONS_in_optionsSpec714); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_OPTIONS.add(OPTIONS28);

            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:135:12: ( option ';' )+
            int cnt13=0;
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==TOKEN_REF||LA13_0==RULE_REF) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:135:13: option ';'
            	    {
            	    pushFollow(FOLLOW_option_in_optionsSpec717);
            	    option29=option();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_option.add(option29.getTree());
            	    char_literal30=(Token)match(input,69,FOLLOW_69_in_optionsSpec719); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_69.add(char_literal30);


            	    }
            	    break;

            	default :
            	    if ( cnt13 >= 1 ) break loop13;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(13, input);
                        throw eee;
                }
                cnt13++;
            } while (true);

            char_literal31=(Token)match(input,70,FOLLOW_70_in_optionsSpec723); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_70.add(char_literal31);



            // AST REWRITE
            // elements: OPTIONS, option
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 135:30: -> ^( OPTIONS ( option )+ )
            {
                // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:135:33: ^( OPTIONS ( option )+ )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_OPTIONS.nextNode(), root_1);

                if ( !(stream_option.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_option.hasNext() ) {
                    adaptor.addChild(root_1, stream_option.nextTree());

                }
                stream_option.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "optionsSpec"

    public static class option_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "option"
    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:138:1: option : id '=' optionValue -> ^( '=' id optionValue ) ;
    public final ANTLRv3Parser.option_return option() throws RecognitionException {
        ANTLRv3Parser.option_return retval = new ANTLRv3Parser.option_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal33=null;
        ANTLRv3Parser.id_return id32 = null;

        ANTLRv3Parser.optionValue_return optionValue34 = null;


        CommonTree char_literal33_tree=null;
        RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
        RewriteRuleSubtreeStream stream_id=new RewriteRuleSubtreeStream(adaptor,"rule id");
        RewriteRuleSubtreeStream stream_optionValue=new RewriteRuleSubtreeStream(adaptor,"rule optionValue");
        try {
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:139:5: ( id '=' optionValue -> ^( '=' id optionValue ) )
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:139:9: id '=' optionValue
            {
            pushFollow(FOLLOW_id_in_option748);
            id32=id();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_id.add(id32.getTree());
            char_literal33=(Token)match(input,71,FOLLOW_71_in_option750); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_71.add(char_literal33);

            pushFollow(FOLLOW_optionValue_in_option752);
            optionValue34=optionValue();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_optionValue.add(optionValue34.getTree());


            // AST REWRITE
            // elements: optionValue, id, 71
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 139:28: -> ^( '=' id optionValue )
            {
                // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:139:31: ^( '=' id optionValue )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_71.nextNode(), root_1);

                adaptor.addChild(root_1, stream_id.nextTree());
                adaptor.addChild(root_1, stream_optionValue.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "option"

    public static class optionValue_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "optionValue"
    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:142:1: optionValue : ( id | STRING_LITERAL | CHAR_LITERAL | INT | s= '*' -> STRING_LITERAL[$s] );
    public final ANTLRv3Parser.optionValue_return optionValue() throws RecognitionException {
        ANTLRv3Parser.optionValue_return retval = new ANTLRv3Parser.optionValue_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token s=null;
        Token STRING_LITERAL36=null;
        Token CHAR_LITERAL37=null;
        Token INT38=null;
        ANTLRv3Parser.id_return id35 = null;


        CommonTree s_tree=null;
        CommonTree STRING_LITERAL36_tree=null;
        CommonTree CHAR_LITERAL37_tree=null;
        CommonTree INT38_tree=null;
        RewriteRuleTokenStream stream_74=new RewriteRuleTokenStream(adaptor,"token 74");

        try {
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:143:5: ( id | STRING_LITERAL | CHAR_LITERAL | INT | s= '*' -> STRING_LITERAL[$s] )
            int alt14=5;
            switch ( input.LA(1) ) {
            case TOKEN_REF:
            case RULE_REF:
                {
                alt14=1;
                }
                break;
            case STRING_LITERAL:
                {
                alt14=2;
                }
                break;
            case CHAR_LITERAL:
                {
                alt14=3;
                }
                break;
            case INT:
                {
                alt14=4;
                }
                break;
            case 74:
                {
                alt14=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;
            }

            switch (alt14) {
                case 1 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:143:9: id
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_id_in_optionValue781);
                    id35=id();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, id35.getTree());

                    }
                    break;
                case 2 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:144:9: STRING_LITERAL
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    STRING_LITERAL36=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_optionValue791); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    STRING_LITERAL36_tree = (CommonTree)adaptor.create(STRING_LITERAL36);
                    adaptor.addChild(root_0, STRING_LITERAL36_tree);
                    }

                    }
                    break;
                case 3 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:145:9: CHAR_LITERAL
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    CHAR_LITERAL37=(Token)match(input,CHAR_LITERAL,FOLLOW_CHAR_LITERAL_in_optionValue801); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    CHAR_LITERAL37_tree = (CommonTree)adaptor.create(CHAR_LITERAL37);
                    adaptor.addChild(root_0, CHAR_LITERAL37_tree);
                    }

                    }
                    break;
                case 4 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:146:9: INT
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    INT38=(Token)match(input,INT,FOLLOW_INT_in_optionValue811); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    INT38_tree = (CommonTree)adaptor.create(INT38);
                    adaptor.addChild(root_0, INT38_tree);
                    }

                    }
                    break;
                case 5 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:147:7: s= '*'
                    {
                    s=(Token)match(input,74,FOLLOW_74_in_optionValue821); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_74.add(s);



                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 147:13: -> STRING_LITERAL[$s]
                    {
                        adaptor.addChild(root_0, (CommonTree)adaptor.create(STRING_LITERAL, s));

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "optionValue"

    protected static class rule_scope {
        String name;
    }
    protected Stack rule_stack = new Stack();

    public static class rule_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "rule"
    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:150:1: rule : ( DOC_COMMENT )? (modifier= ( 'protected' | 'public' | 'private' | 'fragment' ) )? id ( '!' )? (arg= ARG_ACTION )? ( 'returns' rt= ARG_ACTION )? ( throwsSpec )? ( optionsSpec )? ( ruleScopeSpec )? ( ruleAction )* ':' altList ';' ( exceptionGroup )? -> ^( RULE id ( ^( ARG $arg) )? ( ^( RET $rt) )? ( optionsSpec )? ( ruleScopeSpec )? ( ruleAction )* altList ( exceptionGroup )? EOR[\"EOR\"] ) ;
    public final ANTLRv3Parser.rule_return rule() throws RecognitionException {
        rule_stack.push(new rule_scope());
        ANTLRv3Parser.rule_return retval = new ANTLRv3Parser.rule_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token modifier=null;
        Token arg=null;
        Token rt=null;
        Token DOC_COMMENT39=null;
        Token string_literal40=null;
        Token string_literal41=null;
        Token string_literal42=null;
        Token string_literal43=null;
        Token char_literal45=null;
        Token string_literal46=null;
        Token char_literal51=null;
        Token char_literal53=null;
        ANTLRv3Parser.id_return id44 = null;

        ANTLRv3Parser.throwsSpec_return throwsSpec47 = null;

        ANTLRv3Parser.optionsSpec_return optionsSpec48 = null;

        ANTLRv3Parser.ruleScopeSpec_return ruleScopeSpec49 = null;

        ANTLRv3Parser.ruleAction_return ruleAction50 = null;

        ANTLRv3Parser.altList_return altList52 = null;

        ANTLRv3Parser.exceptionGroup_return exceptionGroup54 = null;


        CommonTree modifier_tree=null;
        CommonTree arg_tree=null;
        CommonTree rt_tree=null;
        CommonTree DOC_COMMENT39_tree=null;
        CommonTree string_literal40_tree=null;
        CommonTree string_literal41_tree=null;
        CommonTree string_literal42_tree=null;
        CommonTree string_literal43_tree=null;
        CommonTree char_literal45_tree=null;
        CommonTree string_literal46_tree=null;
        CommonTree char_literal51_tree=null;
        CommonTree char_literal53_tree=null;
        RewriteRuleTokenStream stream_DOC_COMMENT=new RewriteRuleTokenStream(adaptor,"token DOC_COMMENT");
        RewriteRuleTokenStream stream_79=new RewriteRuleTokenStream(adaptor,"token 79");
        RewriteRuleTokenStream stream_78=new RewriteRuleTokenStream(adaptor,"token 78");
        RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
        RewriteRuleTokenStream stream_77=new RewriteRuleTokenStream(adaptor,"token 77");
        RewriteRuleTokenStream stream_BANG=new RewriteRuleTokenStream(adaptor,"token BANG");
        RewriteRuleTokenStream stream_FRAGMENT=new RewriteRuleTokenStream(adaptor,"token FRAGMENT");
        RewriteRuleTokenStream stream_75=new RewriteRuleTokenStream(adaptor,"token 75");
        RewriteRuleTokenStream stream_ARG_ACTION=new RewriteRuleTokenStream(adaptor,"token ARG_ACTION");
        RewriteRuleTokenStream stream_76=new RewriteRuleTokenStream(adaptor,"token 76");
        RewriteRuleSubtreeStream stream_id=new RewriteRuleSubtreeStream(adaptor,"rule id");
        RewriteRuleSubtreeStream stream_exceptionGroup=new RewriteRuleSubtreeStream(adaptor,"rule exceptionGroup");
        RewriteRuleSubtreeStream stream_throwsSpec=new RewriteRuleSubtreeStream(adaptor,"rule throwsSpec");
        RewriteRuleSubtreeStream stream_ruleScopeSpec=new RewriteRuleSubtreeStream(adaptor,"rule ruleScopeSpec");
        RewriteRuleSubtreeStream stream_optionsSpec=new RewriteRuleSubtreeStream(adaptor,"rule optionsSpec");
        RewriteRuleSubtreeStream stream_altList=new RewriteRuleSubtreeStream(adaptor,"rule altList");
        RewriteRuleSubtreeStream stream_ruleAction=new RewriteRuleSubtreeStream(adaptor,"rule ruleAction");
        try {
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:154:2: ( ( DOC_COMMENT )? (modifier= ( 'protected' | 'public' | 'private' | 'fragment' ) )? id ( '!' )? (arg= ARG_ACTION )? ( 'returns' rt= ARG_ACTION )? ( throwsSpec )? ( optionsSpec )? ( ruleScopeSpec )? ( ruleAction )* ':' altList ';' ( exceptionGroup )? -> ^( RULE id ( ^( ARG $arg) )? ( ^( RET $rt) )? ( optionsSpec )? ( ruleScopeSpec )? ( ruleAction )* altList ( exceptionGroup )? EOR[\"EOR\"] ) )
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:154:4: ( DOC_COMMENT )? (modifier= ( 'protected' | 'public' | 'private' | 'fragment' ) )? id ( '!' )? (arg= ARG_ACTION )? ( 'returns' rt= ARG_ACTION )? ( throwsSpec )? ( optionsSpec )? ( ruleScopeSpec )? ( ruleAction )* ':' altList ';' ( exceptionGroup )?
            {
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:154:4: ( DOC_COMMENT )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==DOC_COMMENT) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:154:4: DOC_COMMENT
                    {
                    DOC_COMMENT39=(Token)match(input,DOC_COMMENT,FOLLOW_DOC_COMMENT_in_rule846); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_DOC_COMMENT.add(DOC_COMMENT39);


                    }
                    break;

            }

            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:155:3: (modifier= ( 'protected' | 'public' | 'private' | 'fragment' ) )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==FRAGMENT||(LA17_0>=75 && LA17_0<=77)) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:155:5: modifier= ( 'protected' | 'public' | 'private' | 'fragment' )
                    {
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:155:14: ( 'protected' | 'public' | 'private' | 'fragment' )
                    int alt16=4;
                    switch ( input.LA(1) ) {
                    case 75:
                        {
                        alt16=1;
                        }
                        break;
                    case 76:
                        {
                        alt16=2;
                        }
                        break;
                    case 77:
                        {
                        alt16=3;
                        }
                        break;
                    case FRAGMENT:
                        {
                        alt16=4;
                        }
                        break;
                    default:
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 16, 0, input);

                        throw nvae;
                    }

                    switch (alt16) {
                        case 1 :
                            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:155:15: 'protected'
                            {
                            string_literal40=(Token)match(input,75,FOLLOW_75_in_rule856); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_75.add(string_literal40);


                            }
                            break;
                        case 2 :
                            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:155:27: 'public'
                            {
                            string_literal41=(Token)match(input,76,FOLLOW_76_in_rule858); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_76.add(string_literal41);


                            }
                            break;
                        case 3 :
                            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:155:36: 'private'
                            {
                            string_literal42=(Token)match(input,77,FOLLOW_77_in_rule860); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_77.add(string_literal42);


                            }
                            break;
                        case 4 :
                            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:155:46: 'fragment'
                            {
                            string_literal43=(Token)match(input,FRAGMENT,FOLLOW_FRAGMENT_in_rule862); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_FRAGMENT.add(string_literal43);


                            }
                            break;

                    }


                    }
                    break;

            }

            pushFollow(FOLLOW_id_in_rule870);
            id44=id();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_id.add(id44.getTree());
            if ( state.backtracking==0 ) {
              ((rule_scope)rule_stack.peek()).name = (id44!=null?input.toString(id44.start,id44.stop):null);
            }
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:157:3: ( '!' )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==BANG) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:157:3: '!'
                    {
                    char_literal45=(Token)match(input,BANG,FOLLOW_BANG_in_rule876); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_BANG.add(char_literal45);


                    }
                    break;

            }

            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:158:3: (arg= ARG_ACTION )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==ARG_ACTION) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:158:5: arg= ARG_ACTION
                    {
                    arg=(Token)match(input,ARG_ACTION,FOLLOW_ARG_ACTION_in_rule885); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ARG_ACTION.add(arg);


                    }
                    break;

            }

            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:159:3: ( 'returns' rt= ARG_ACTION )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==78) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:159:5: 'returns' rt= ARG_ACTION
                    {
                    string_literal46=(Token)match(input,78,FOLLOW_78_in_rule894); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_78.add(string_literal46);

                    rt=(Token)match(input,ARG_ACTION,FOLLOW_ARG_ACTION_in_rule898); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ARG_ACTION.add(rt);


                    }
                    break;

            }

            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:160:3: ( throwsSpec )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==80) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:160:3: throwsSpec
                    {
                    pushFollow(FOLLOW_throwsSpec_in_rule906);
                    throwsSpec47=throwsSpec();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_throwsSpec.add(throwsSpec47.getTree());

                    }
                    break;

            }

            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:160:15: ( optionsSpec )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==OPTIONS) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:160:15: optionsSpec
                    {
                    pushFollow(FOLLOW_optionsSpec_in_rule909);
                    optionsSpec48=optionsSpec();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_optionsSpec.add(optionsSpec48.getTree());

                    }
                    break;

            }

            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:160:28: ( ruleScopeSpec )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==SCOPE) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:160:28: ruleScopeSpec
                    {
                    pushFollow(FOLLOW_ruleScopeSpec_in_rule912);
                    ruleScopeSpec49=ruleScopeSpec();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_ruleScopeSpec.add(ruleScopeSpec49.getTree());

                    }
                    break;

            }

            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:160:43: ( ruleAction )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==72) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:160:43: ruleAction
            	    {
            	    pushFollow(FOLLOW_ruleAction_in_rule915);
            	    ruleAction50=ruleAction();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_ruleAction.add(ruleAction50.getTree());

            	    }
            	    break;

            	default :
            	    break loop24;
                }
            } while (true);

            char_literal51=(Token)match(input,79,FOLLOW_79_in_rule920); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_79.add(char_literal51);

            pushFollow(FOLLOW_altList_in_rule922);
            altList52=altList();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_altList.add(altList52.getTree());
            char_literal53=(Token)match(input,69,FOLLOW_69_in_rule924); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_69.add(char_literal53);

            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:162:3: ( exceptionGroup )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( ((LA25_0>=85 && LA25_0<=86)) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:162:3: exceptionGroup
                    {
                    pushFollow(FOLLOW_exceptionGroup_in_rule928);
                    exceptionGroup54=exceptionGroup();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_exceptionGroup.add(exceptionGroup54.getTree());

                    }
                    break;

            }



            // AST REWRITE
            // elements: altList, optionsSpec, id, exceptionGroup, arg, ruleScopeSpec, rt, ruleAction
            // token labels: arg, rt
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleTokenStream stream_arg=new RewriteRuleTokenStream(adaptor,"token arg",arg);
            RewriteRuleTokenStream stream_rt=new RewriteRuleTokenStream(adaptor,"token rt",rt);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 163:6: -> ^( RULE id ( ^( ARG $arg) )? ( ^( RET $rt) )? ( optionsSpec )? ( ruleScopeSpec )? ( ruleAction )* altList ( exceptionGroup )? EOR[\"EOR\"] )
            {
                // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:163:9: ^( RULE id ( ^( ARG $arg) )? ( ^( RET $rt) )? ( optionsSpec )? ( ruleScopeSpec )? ( ruleAction )* altList ( exceptionGroup )? EOR[\"EOR\"] )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(RULE, "RULE"), root_1);

                adaptor.addChild(root_1, stream_id.nextTree());
                adaptor.addChild(root_1, modifier!=null?adaptor.create(modifier):null);
                // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:163:67: ( ^( ARG $arg) )?
                if ( stream_arg.hasNext() ) {
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:163:67: ^( ARG $arg)
                    {
                    CommonTree root_2 = (CommonTree)adaptor.nil();
                    root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ARG, "ARG"), root_2);

                    adaptor.addChild(root_2, stream_arg.nextNode());

                    adaptor.addChild(root_1, root_2);
                    }

                }
                stream_arg.reset();
                // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:163:80: ( ^( RET $rt) )?
                if ( stream_rt.hasNext() ) {
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:163:80: ^( RET $rt)
                    {
                    CommonTree root_2 = (CommonTree)adaptor.nil();
                    root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(RET, "RET"), root_2);

                    adaptor.addChild(root_2, stream_rt.nextNode());

                    adaptor.addChild(root_1, root_2);
                    }

                }
                stream_rt.reset();
                // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:164:9: ( optionsSpec )?
                if ( stream_optionsSpec.hasNext() ) {
                    adaptor.addChild(root_1, stream_optionsSpec.nextTree());

                }
                stream_optionsSpec.reset();
                // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:164:22: ( ruleScopeSpec )?
                if ( stream_ruleScopeSpec.hasNext() ) {
                    adaptor.addChild(root_1, stream_ruleScopeSpec.nextTree());

                }
                stream_ruleScopeSpec.reset();
                // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:164:37: ( ruleAction )*
                while ( stream_ruleAction.hasNext() ) {
                    adaptor.addChild(root_1, stream_ruleAction.nextTree());

                }
                stream_ruleAction.reset();
                adaptor.addChild(root_1, stream_altList.nextTree());
                // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:166:9: ( exceptionGroup )?
                if ( stream_exceptionGroup.hasNext() ) {
                    adaptor.addChild(root_1, stream_exceptionGroup.nextTree());

                }
                stream_exceptionGroup.reset();
                adaptor.addChild(root_1, (CommonTree)adaptor.create(EOR, "EOR"));

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            rule_stack.pop();
        }
        return retval;
    }
    // $ANTLR end "rule"

    public static class ruleAction_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "ruleAction"
    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:171:1: ruleAction : '@' id ACTION -> ^( '@' id ACTION ) ;
    public final ANTLRv3Parser.ruleAction_return ruleAction() throws RecognitionException {
        ANTLRv3Parser.ruleAction_return retval = new ANTLRv3Parser.ruleAction_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal55=null;
        Token ACTION57=null;
        ANTLRv3Parser.id_return id56 = null;


        CommonTree char_literal55_tree=null;
        CommonTree ACTION57_tree=null;
        RewriteRuleTokenStream stream_72=new RewriteRuleTokenStream(adaptor,"token 72");
        RewriteRuleTokenStream stream_ACTION=new RewriteRuleTokenStream(adaptor,"token ACTION");
        RewriteRuleSubtreeStream stream_id=new RewriteRuleSubtreeStream(adaptor,"rule id");
        try {
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:173:2: ( '@' id ACTION -> ^( '@' id ACTION ) )
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:173:4: '@' id ACTION
            {
            char_literal55=(Token)match(input,72,FOLLOW_72_in_ruleAction1030); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_72.add(char_literal55);

            pushFollow(FOLLOW_id_in_ruleAction1032);
            id56=id();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_id.add(id56.getTree());
            ACTION57=(Token)match(input,ACTION,FOLLOW_ACTION_in_ruleAction1034); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ACTION.add(ACTION57);



            // AST REWRITE
            // elements: id, 72, ACTION
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 173:18: -> ^( '@' id ACTION )
            {
                // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:173:21: ^( '@' id ACTION )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_72.nextNode(), root_1);

                adaptor.addChild(root_1, stream_id.nextTree());
                adaptor.addChild(root_1, stream_ACTION.nextNode());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "ruleAction"

    public static class throwsSpec_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "throwsSpec"
    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:176:1: throwsSpec : 'throws' id ( ',' id )* -> ^( 'throws' ( id )+ ) ;
    public final ANTLRv3Parser.throwsSpec_return throwsSpec() throws RecognitionException {
        ANTLRv3Parser.throwsSpec_return retval = new ANTLRv3Parser.throwsSpec_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal58=null;
        Token char_literal60=null;
        ANTLRv3Parser.id_return id59 = null;

        ANTLRv3Parser.id_return id61 = null;


        CommonTree string_literal58_tree=null;
        CommonTree char_literal60_tree=null;
        RewriteRuleTokenStream stream_80=new RewriteRuleTokenStream(adaptor,"token 80");
        RewriteRuleTokenStream stream_81=new RewriteRuleTokenStream(adaptor,"token 81");
        RewriteRuleSubtreeStream stream_id=new RewriteRuleSubtreeStream(adaptor,"rule id");
        try {
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:177:2: ( 'throws' id ( ',' id )* -> ^( 'throws' ( id )+ ) )
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:177:4: 'throws' id ( ',' id )*
            {
            string_literal58=(Token)match(input,80,FOLLOW_80_in_throwsSpec1055); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_80.add(string_literal58);

            pushFollow(FOLLOW_id_in_throwsSpec1057);
            id59=id();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_id.add(id59.getTree());
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:177:16: ( ',' id )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0==81) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:177:18: ',' id
            	    {
            	    char_literal60=(Token)match(input,81,FOLLOW_81_in_throwsSpec1061); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_81.add(char_literal60);

            	    pushFollow(FOLLOW_id_in_throwsSpec1063);
            	    id61=id();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_id.add(id61.getTree());

            	    }
            	    break;

            	default :
            	    break loop26;
                }
            } while (true);



            // AST REWRITE
            // elements: 80, id
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 177:28: -> ^( 'throws' ( id )+ )
            {
                // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:177:31: ^( 'throws' ( id )+ )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_80.nextNode(), root_1);

                if ( !(stream_id.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_id.hasNext() ) {
                    adaptor.addChild(root_1, stream_id.nextTree());

                }
                stream_id.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "throwsSpec"

    public static class ruleScopeSpec_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "ruleScopeSpec"
    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:180:1: ruleScopeSpec : ( 'scope' ACTION -> ^( 'scope' ACTION ) | 'scope' id ( ',' id )* ';' -> ^( 'scope' ( id )+ ) | 'scope' ACTION 'scope' id ( ',' id )* ';' -> ^( 'scope' ACTION ( id )+ ) );
    public final ANTLRv3Parser.ruleScopeSpec_return ruleScopeSpec() throws RecognitionException {
        ANTLRv3Parser.ruleScopeSpec_return retval = new ANTLRv3Parser.ruleScopeSpec_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal62=null;
        Token ACTION63=null;
        Token string_literal64=null;
        Token char_literal66=null;
        Token char_literal68=null;
        Token string_literal69=null;
        Token ACTION70=null;
        Token string_literal71=null;
        Token char_literal73=null;
        Token char_literal75=null;
        ANTLRv3Parser.id_return id65 = null;

        ANTLRv3Parser.id_return id67 = null;

        ANTLRv3Parser.id_return id72 = null;

        ANTLRv3Parser.id_return id74 = null;


        CommonTree string_literal62_tree=null;
        CommonTree ACTION63_tree=null;
        CommonTree string_literal64_tree=null;
        CommonTree char_literal66_tree=null;
        CommonTree char_literal68_tree=null;
        CommonTree string_literal69_tree=null;
        CommonTree ACTION70_tree=null;
        CommonTree string_literal71_tree=null;
        CommonTree char_literal73_tree=null;
        CommonTree char_literal75_tree=null;
        RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
        RewriteRuleTokenStream stream_SCOPE=new RewriteRuleTokenStream(adaptor,"token SCOPE");
        RewriteRuleTokenStream stream_81=new RewriteRuleTokenStream(adaptor,"token 81");
        RewriteRuleTokenStream stream_ACTION=new RewriteRuleTokenStream(adaptor,"token ACTION");
        RewriteRuleSubtreeStream stream_id=new RewriteRuleSubtreeStream(adaptor,"rule id");
        try {
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:181:2: ( 'scope' ACTION -> ^( 'scope' ACTION ) | 'scope' id ( ',' id )* ';' -> ^( 'scope' ( id )+ ) | 'scope' ACTION 'scope' id ( ',' id )* ';' -> ^( 'scope' ACTION ( id )+ ) )
            int alt29=3;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==SCOPE) ) {
                int LA29_1 = input.LA(2);

                if ( (LA29_1==ACTION) ) {
                    int LA29_2 = input.LA(3);

                    if ( (LA29_2==SCOPE) ) {
                        alt29=3;
                    }
                    else if ( (LA29_2==72||LA29_2==79) ) {
                        alt29=1;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 29, 2, input);

                        throw nvae;
                    }
                }
                else if ( (LA29_1==TOKEN_REF||LA29_1==RULE_REF) ) {
                    alt29=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 29, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 29, 0, input);

                throw nvae;
            }
            switch (alt29) {
                case 1 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:181:4: 'scope' ACTION
                    {
                    string_literal62=(Token)match(input,SCOPE,FOLLOW_SCOPE_in_ruleScopeSpec1086); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SCOPE.add(string_literal62);

                    ACTION63=(Token)match(input,ACTION,FOLLOW_ACTION_in_ruleScopeSpec1088); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ACTION.add(ACTION63);



                    // AST REWRITE
                    // elements: ACTION, SCOPE
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 181:19: -> ^( 'scope' ACTION )
                    {
                        // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:181:22: ^( 'scope' ACTION )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_SCOPE.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_ACTION.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:182:4: 'scope' id ( ',' id )* ';'
                    {
                    string_literal64=(Token)match(input,SCOPE,FOLLOW_SCOPE_in_ruleScopeSpec1101); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SCOPE.add(string_literal64);

                    pushFollow(FOLLOW_id_in_ruleScopeSpec1103);
                    id65=id();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_id.add(id65.getTree());
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:182:15: ( ',' id )*
                    loop27:
                    do {
                        int alt27=2;
                        int LA27_0 = input.LA(1);

                        if ( (LA27_0==81) ) {
                            alt27=1;
                        }


                        switch (alt27) {
                    	case 1 :
                    	    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:182:16: ',' id
                    	    {
                    	    char_literal66=(Token)match(input,81,FOLLOW_81_in_ruleScopeSpec1106); if (state.failed) return retval; 
                    	    if ( state.backtracking==0 ) stream_81.add(char_literal66);

                    	    pushFollow(FOLLOW_id_in_ruleScopeSpec1108);
                    	    id67=id();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_id.add(id67.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop27;
                        }
                    } while (true);

                    char_literal68=(Token)match(input,69,FOLLOW_69_in_ruleScopeSpec1112); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_69.add(char_literal68);



                    // AST REWRITE
                    // elements: SCOPE, id
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 182:29: -> ^( 'scope' ( id )+ )
                    {
                        // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:182:32: ^( 'scope' ( id )+ )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_SCOPE.nextNode(), root_1);

                        if ( !(stream_id.hasNext()) ) {
                            throw new RewriteEarlyExitException();
                        }
                        while ( stream_id.hasNext() ) {
                            adaptor.addChild(root_1, stream_id.nextTree());

                        }
                        stream_id.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 3 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:183:4: 'scope' ACTION 'scope' id ( ',' id )* ';'
                    {
                    string_literal69=(Token)match(input,SCOPE,FOLLOW_SCOPE_in_ruleScopeSpec1126); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SCOPE.add(string_literal69);

                    ACTION70=(Token)match(input,ACTION,FOLLOW_ACTION_in_ruleScopeSpec1128); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ACTION.add(ACTION70);

                    string_literal71=(Token)match(input,SCOPE,FOLLOW_SCOPE_in_ruleScopeSpec1132); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SCOPE.add(string_literal71);

                    pushFollow(FOLLOW_id_in_ruleScopeSpec1134);
                    id72=id();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_id.add(id72.getTree());
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:184:14: ( ',' id )*
                    loop28:
                    do {
                        int alt28=2;
                        int LA28_0 = input.LA(1);

                        if ( (LA28_0==81) ) {
                            alt28=1;
                        }


                        switch (alt28) {
                    	case 1 :
                    	    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:184:15: ',' id
                    	    {
                    	    char_literal73=(Token)match(input,81,FOLLOW_81_in_ruleScopeSpec1137); if (state.failed) return retval; 
                    	    if ( state.backtracking==0 ) stream_81.add(char_literal73);

                    	    pushFollow(FOLLOW_id_in_ruleScopeSpec1139);
                    	    id74=id();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_id.add(id74.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop28;
                        }
                    } while (true);

                    char_literal75=(Token)match(input,69,FOLLOW_69_in_ruleScopeSpec1143); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_69.add(char_literal75);



                    // AST REWRITE
                    // elements: SCOPE, ACTION, id
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 185:3: -> ^( 'scope' ACTION ( id )+ )
                    {
                        // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:185:6: ^( 'scope' ACTION ( id )+ )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_SCOPE.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_ACTION.nextNode());
                        if ( !(stream_id.hasNext()) ) {
                            throw new RewriteEarlyExitException();
                        }
                        while ( stream_id.hasNext() ) {
                            adaptor.addChild(root_1, stream_id.nextTree());

                        }
                        stream_id.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "ruleScopeSpec"

    public static class block_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "block"
    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:188:1: block : lp= '(' ( (opts= optionsSpec )? ':' )? a1= alternative rewrite ( '|' a2= alternative rewrite )* rp= ')' -> ^( BLOCK[$lp,\"BLOCK\"] ( optionsSpec )? ( alternative ( rewrite )? )+ EOB[$rp,\"EOB\"] ) ;
    public final ANTLRv3Parser.block_return block() throws RecognitionException {
        ANTLRv3Parser.block_return retval = new ANTLRv3Parser.block_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token lp=null;
        Token rp=null;
        Token char_literal76=null;
        Token char_literal78=null;
        ANTLRv3Parser.optionsSpec_return opts = null;

        ANTLRv3Parser.alternative_return a1 = null;

        ANTLRv3Parser.alternative_return a2 = null;

        ANTLRv3Parser.rewrite_return rewrite77 = null;

        ANTLRv3Parser.rewrite_return rewrite79 = null;


        CommonTree lp_tree=null;
        CommonTree rp_tree=null;
        CommonTree char_literal76_tree=null;
        CommonTree char_literal78_tree=null;
        RewriteRuleTokenStream stream_79=new RewriteRuleTokenStream(adaptor,"token 79");
        RewriteRuleTokenStream stream_82=new RewriteRuleTokenStream(adaptor,"token 82");
        RewriteRuleTokenStream stream_83=new RewriteRuleTokenStream(adaptor,"token 83");
        RewriteRuleTokenStream stream_84=new RewriteRuleTokenStream(adaptor,"token 84");
        RewriteRuleSubtreeStream stream_rewrite=new RewriteRuleSubtreeStream(adaptor,"rule rewrite");
        RewriteRuleSubtreeStream stream_alternative=new RewriteRuleSubtreeStream(adaptor,"rule alternative");
        RewriteRuleSubtreeStream stream_optionsSpec=new RewriteRuleSubtreeStream(adaptor,"rule optionsSpec");
        try {
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:189:5: (lp= '(' ( (opts= optionsSpec )? ':' )? a1= alternative rewrite ( '|' a2= alternative rewrite )* rp= ')' -> ^( BLOCK[$lp,\"BLOCK\"] ( optionsSpec )? ( alternative ( rewrite )? )+ EOB[$rp,\"EOB\"] ) )
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:189:9: lp= '(' ( (opts= optionsSpec )? ':' )? a1= alternative rewrite ( '|' a2= alternative rewrite )* rp= ')'
            {
            lp=(Token)match(input,82,FOLLOW_82_in_block1175); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_82.add(lp);

            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:190:3: ( (opts= optionsSpec )? ':' )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==OPTIONS||LA31_0==79) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:190:5: (opts= optionsSpec )? ':'
                    {
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:190:5: (opts= optionsSpec )?
                    int alt30=2;
                    int LA30_0 = input.LA(1);

                    if ( (LA30_0==OPTIONS) ) {
                        alt30=1;
                    }
                    switch (alt30) {
                        case 1 :
                            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:190:6: opts= optionsSpec
                            {
                            pushFollow(FOLLOW_optionsSpec_in_block1184);
                            opts=optionsSpec();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_optionsSpec.add(opts.getTree());

                            }
                            break;

                    }

                    char_literal76=(Token)match(input,79,FOLLOW_79_in_block1188); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_79.add(char_literal76);


                    }
                    break;

            }

            pushFollow(FOLLOW_alternative_in_block1197);
            a1=alternative();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_alternative.add(a1.getTree());
            pushFollow(FOLLOW_rewrite_in_block1199);
            rewrite77=rewrite();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_rewrite.add(rewrite77.getTree());
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:191:26: ( '|' a2= alternative rewrite )*
            loop32:
            do {
                int alt32=2;
                int LA32_0 = input.LA(1);

                if ( (LA32_0==83) ) {
                    alt32=1;
                }


                switch (alt32) {
            	case 1 :
            	    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:191:28: '|' a2= alternative rewrite
            	    {
            	    char_literal78=(Token)match(input,83,FOLLOW_83_in_block1203); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_83.add(char_literal78);

            	    pushFollow(FOLLOW_alternative_in_block1207);
            	    a2=alternative();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_alternative.add(a2.getTree());
            	    pushFollow(FOLLOW_rewrite_in_block1209);
            	    rewrite79=rewrite();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_rewrite.add(rewrite79.getTree());

            	    }
            	    break;

            	default :
            	    break loop32;
                }
            } while (true);

            rp=(Token)match(input,84,FOLLOW_84_in_block1224); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_84.add(rp);



            // AST REWRITE
            // elements: rewrite, alternative, optionsSpec
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 193:9: -> ^( BLOCK[$lp,\"BLOCK\"] ( optionsSpec )? ( alternative ( rewrite )? )+ EOB[$rp,\"EOB\"] )
            {
                // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:193:12: ^( BLOCK[$lp,\"BLOCK\"] ( optionsSpec )? ( alternative ( rewrite )? )+ EOB[$rp,\"EOB\"] )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(BLOCK, lp, "BLOCK"), root_1);

                // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:193:34: ( optionsSpec )?
                if ( stream_optionsSpec.hasNext() ) {
                    adaptor.addChild(root_1, stream_optionsSpec.nextTree());

                }
                stream_optionsSpec.reset();
                if ( !(stream_alternative.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_alternative.hasNext() ) {
                    adaptor.addChild(root_1, stream_alternative.nextTree());
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:193:60: ( rewrite )?
                    if ( stream_rewrite.hasNext() ) {
                        adaptor.addChild(root_1, stream_rewrite.nextTree());

                    }
                    stream_rewrite.reset();

                }
                stream_alternative.reset();
                adaptor.addChild(root_1, (CommonTree)adaptor.create(EOB, rp, "EOB"));

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "block"

    public static class altList_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "altList"
    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:196:1: altList : a1= alternative rewrite ( '|' a2= alternative rewrite )* -> ^( ( alternative ( rewrite )? )+ EOB[\"EOB\"] ) ;
    public final ANTLRv3Parser.altList_return altList() throws RecognitionException {
        ANTLRv3Parser.altList_return retval = new ANTLRv3Parser.altList_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal81=null;
        ANTLRv3Parser.alternative_return a1 = null;

        ANTLRv3Parser.alternative_return a2 = null;

        ANTLRv3Parser.rewrite_return rewrite80 = null;

        ANTLRv3Parser.rewrite_return rewrite82 = null;


        CommonTree char_literal81_tree=null;
        RewriteRuleTokenStream stream_83=new RewriteRuleTokenStream(adaptor,"token 83");
        RewriteRuleSubtreeStream stream_rewrite=new RewriteRuleSubtreeStream(adaptor,"rule rewrite");
        RewriteRuleSubtreeStream stream_alternative=new RewriteRuleSubtreeStream(adaptor,"rule alternative");

        	// must create root manually as it's used by invoked rules in real antlr tool.
        	// leave here to demonstrate use of {...} in rewrite rule
        	// it's really BLOCK[firstToken,"BLOCK"]; set line/col to previous ( or : token.
            CommonTree blkRoot = (CommonTree)adaptor.create(BLOCK,input.LT(-1),"BLOCK");

        try {
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:203:5: (a1= alternative rewrite ( '|' a2= alternative rewrite )* -> ^( ( alternative ( rewrite )? )+ EOB[\"EOB\"] ) )
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:203:9: a1= alternative rewrite ( '|' a2= alternative rewrite )*
            {
            pushFollow(FOLLOW_alternative_in_altList1281);
            a1=alternative();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_alternative.add(a1.getTree());
            pushFollow(FOLLOW_rewrite_in_altList1283);
            rewrite80=rewrite();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_rewrite.add(rewrite80.getTree());
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:203:32: ( '|' a2= alternative rewrite )*
            loop33:
            do {
                int alt33=2;
                int LA33_0 = input.LA(1);

                if ( (LA33_0==83) ) {
                    alt33=1;
                }


                switch (alt33) {
            	case 1 :
            	    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:203:34: '|' a2= alternative rewrite
            	    {
            	    char_literal81=(Token)match(input,83,FOLLOW_83_in_altList1287); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_83.add(char_literal81);

            	    pushFollow(FOLLOW_alternative_in_altList1291);
            	    a2=alternative();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_alternative.add(a2.getTree());
            	    pushFollow(FOLLOW_rewrite_in_altList1293);
            	    rewrite82=rewrite();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_rewrite.add(rewrite82.getTree());

            	    }
            	    break;

            	default :
            	    break loop33;
                }
            } while (true);



            // AST REWRITE
            // elements: rewrite, alternative
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 204:3: -> ^( ( alternative ( rewrite )? )+ EOB[\"EOB\"] )
            {
                // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:204:6: ^( ( alternative ( rewrite )? )+ EOB[\"EOB\"] )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(blkRoot, root_1);

                if ( !(stream_alternative.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_alternative.hasNext() ) {
                    adaptor.addChild(root_1, stream_alternative.nextTree());
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:204:32: ( rewrite )?
                    if ( stream_rewrite.hasNext() ) {
                        adaptor.addChild(root_1, stream_rewrite.nextTree());

                    }
                    stream_rewrite.reset();

                }
                stream_alternative.reset();
                adaptor.addChild(root_1, (CommonTree)adaptor.create(EOB, "EOB"));

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "altList"

    public static class alternative_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "alternative"
    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:207:1: alternative : ( ( element )+ -> ^( ALT[firstToken,\"ALT\"] ( element )+ EOA[\"EOA\"] ) | -> ^( ALT[prevToken,\"ALT\"] EPSILON[prevToken,\"EPSILON\"] EOA[\"EOA\"] ) );
    public final ANTLRv3Parser.alternative_return alternative() throws RecognitionException {
        ANTLRv3Parser.alternative_return retval = new ANTLRv3Parser.alternative_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        ANTLRv3Parser.element_return element83 = null;


        RewriteRuleSubtreeStream stream_element=new RewriteRuleSubtreeStream(adaptor,"rule element");

        	Token firstToken = input.LT(1);
        	Token prevToken = input.LT(-1); // either : or | I think

        try {
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:212:5: ( ( element )+ -> ^( ALT[firstToken,\"ALT\"] ( element )+ EOA[\"EOA\"] ) | -> ^( ALT[prevToken,\"ALT\"] EPSILON[prevToken,\"EPSILON\"] EOA[\"EOA\"] ) )
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==SEMPRED||LA35_0==TREE_BEGIN||(LA35_0>=TOKEN_REF && LA35_0<=ACTION)||LA35_0==RULE_REF||LA35_0==82||LA35_0==89||LA35_0==92) ) {
                alt35=1;
            }
            else if ( (LA35_0==REWRITE||LA35_0==69||(LA35_0>=83 && LA35_0<=84)) ) {
                alt35=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 35, 0, input);

                throw nvae;
            }
            switch (alt35) {
                case 1 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:212:9: ( element )+
                    {
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:212:9: ( element )+
                    int cnt34=0;
                    loop34:
                    do {
                        int alt34=2;
                        int LA34_0 = input.LA(1);

                        if ( (LA34_0==SEMPRED||LA34_0==TREE_BEGIN||(LA34_0>=TOKEN_REF && LA34_0<=ACTION)||LA34_0==RULE_REF||LA34_0==82||LA34_0==89||LA34_0==92) ) {
                            alt34=1;
                        }


                        switch (alt34) {
                    	case 1 :
                    	    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:212:9: element
                    	    {
                    	    pushFollow(FOLLOW_element_in_alternative1341);
                    	    element83=element();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_element.add(element83.getTree());

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt34 >= 1 ) break loop34;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(34, input);
                                throw eee;
                        }
                        cnt34++;
                    } while (true);



                    // AST REWRITE
                    // elements: element
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 212:18: -> ^( ALT[firstToken,\"ALT\"] ( element )+ EOA[\"EOA\"] )
                    {
                        // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:212:21: ^( ALT[firstToken,\"ALT\"] ( element )+ EOA[\"EOA\"] )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ALT, firstToken, "ALT"), root_1);

                        if ( !(stream_element.hasNext()) ) {
                            throw new RewriteEarlyExitException();
                        }
                        while ( stream_element.hasNext() ) {
                            adaptor.addChild(root_1, stream_element.nextTree());

                        }
                        stream_element.reset();
                        adaptor.addChild(root_1, (CommonTree)adaptor.create(EOA, "EOA"));

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:213:9: 
                    {

                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 213:9: -> ^( ALT[prevToken,\"ALT\"] EPSILON[prevToken,\"EPSILON\"] EOA[\"EOA\"] )
                    {
                        // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:213:12: ^( ALT[prevToken,\"ALT\"] EPSILON[prevToken,\"EPSILON\"] EOA[\"EOA\"] )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ALT, prevToken, "ALT"), root_1);

                        adaptor.addChild(root_1, (CommonTree)adaptor.create(EPSILON, prevToken, "EPSILON"));
                        adaptor.addChild(root_1, (CommonTree)adaptor.create(EOA, "EOA"));

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "alternative"

    public static class exceptionGroup_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "exceptionGroup"
    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:216:1: exceptionGroup : ( ( exceptionHandler )+ ( finallyClause )? | finallyClause );
    public final ANTLRv3Parser.exceptionGroup_return exceptionGroup() throws RecognitionException {
        ANTLRv3Parser.exceptionGroup_return retval = new ANTLRv3Parser.exceptionGroup_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        ANTLRv3Parser.exceptionHandler_return exceptionHandler84 = null;

        ANTLRv3Parser.finallyClause_return finallyClause85 = null;

        ANTLRv3Parser.finallyClause_return finallyClause86 = null;



        try {
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:217:2: ( ( exceptionHandler )+ ( finallyClause )? | finallyClause )
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==85) ) {
                alt38=1;
            }
            else if ( (LA38_0==86) ) {
                alt38=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 38, 0, input);

                throw nvae;
            }
            switch (alt38) {
                case 1 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:217:4: ( exceptionHandler )+ ( finallyClause )?
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:217:4: ( exceptionHandler )+
                    int cnt36=0;
                    loop36:
                    do {
                        int alt36=2;
                        int LA36_0 = input.LA(1);

                        if ( (LA36_0==85) ) {
                            alt36=1;
                        }


                        switch (alt36) {
                    	case 1 :
                    	    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:217:6: exceptionHandler
                    	    {
                    	    pushFollow(FOLLOW_exceptionHandler_in_exceptionGroup1392);
                    	    exceptionHandler84=exceptionHandler();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) adaptor.addChild(root_0, exceptionHandler84.getTree());

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt36 >= 1 ) break loop36;
                    	    if (state.backtracking>0) {state.failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(36, input);
                                throw eee;
                        }
                        cnt36++;
                    } while (true);

                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:217:26: ( finallyClause )?
                    int alt37=2;
                    int LA37_0 = input.LA(1);

                    if ( (LA37_0==86) ) {
                        alt37=1;
                    }
                    switch (alt37) {
                        case 1 :
                            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:217:28: finallyClause
                            {
                            pushFollow(FOLLOW_finallyClause_in_exceptionGroup1399);
                            finallyClause85=finallyClause();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, finallyClause85.getTree());

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:218:4: finallyClause
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_finallyClause_in_exceptionGroup1407);
                    finallyClause86=finallyClause();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, finallyClause86.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "exceptionGroup"

    public static class exceptionHandler_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "exceptionHandler"
    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:221:1: exceptionHandler : 'catch' ARG_ACTION ACTION -> ^( 'catch' ARG_ACTION ACTION ) ;
    public final ANTLRv3Parser.exceptionHandler_return exceptionHandler() throws RecognitionException {
        ANTLRv3Parser.exceptionHandler_return retval = new ANTLRv3Parser.exceptionHandler_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal87=null;
        Token ARG_ACTION88=null;
        Token ACTION89=null;

        CommonTree string_literal87_tree=null;
        CommonTree ARG_ACTION88_tree=null;
        CommonTree ACTION89_tree=null;
        RewriteRuleTokenStream stream_ACTION=new RewriteRuleTokenStream(adaptor,"token ACTION");
        RewriteRuleTokenStream stream_ARG_ACTION=new RewriteRuleTokenStream(adaptor,"token ARG_ACTION");
        RewriteRuleTokenStream stream_85=new RewriteRuleTokenStream(adaptor,"token 85");

        try {
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:222:5: ( 'catch' ARG_ACTION ACTION -> ^( 'catch' ARG_ACTION ACTION ) )
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:222:10: 'catch' ARG_ACTION ACTION
            {
            string_literal87=(Token)match(input,85,FOLLOW_85_in_exceptionHandler1427); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_85.add(string_literal87);

            ARG_ACTION88=(Token)match(input,ARG_ACTION,FOLLOW_ARG_ACTION_in_exceptionHandler1429); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ARG_ACTION.add(ARG_ACTION88);

            ACTION89=(Token)match(input,ACTION,FOLLOW_ACTION_in_exceptionHandler1431); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ACTION.add(ACTION89);



            // AST REWRITE
            // elements: ACTION, ARG_ACTION, 85
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 222:36: -> ^( 'catch' ARG_ACTION ACTION )
            {
                // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:222:39: ^( 'catch' ARG_ACTION ACTION )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_85.nextNode(), root_1);

                adaptor.addChild(root_1, stream_ARG_ACTION.nextNode());
                adaptor.addChild(root_1, stream_ACTION.nextNode());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "exceptionHandler"

    public static class finallyClause_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "finallyClause"
    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:225:1: finallyClause : 'finally' ACTION -> ^( 'finally' ACTION ) ;
    public final ANTLRv3Parser.finallyClause_return finallyClause() throws RecognitionException {
        ANTLRv3Parser.finallyClause_return retval = new ANTLRv3Parser.finallyClause_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal90=null;
        Token ACTION91=null;

        CommonTree string_literal90_tree=null;
        CommonTree ACTION91_tree=null;
        RewriteRuleTokenStream stream_ACTION=new RewriteRuleTokenStream(adaptor,"token ACTION");
        RewriteRuleTokenStream stream_86=new RewriteRuleTokenStream(adaptor,"token 86");

        try {
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:226:5: ( 'finally' ACTION -> ^( 'finally' ACTION ) )
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:226:10: 'finally' ACTION
            {
            string_literal90=(Token)match(input,86,FOLLOW_86_in_finallyClause1461); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_86.add(string_literal90);

            ACTION91=(Token)match(input,ACTION,FOLLOW_ACTION_in_finallyClause1463); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ACTION.add(ACTION91);



            // AST REWRITE
            // elements: ACTION, 86
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 226:27: -> ^( 'finally' ACTION )
            {
                // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:226:30: ^( 'finally' ACTION )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_86.nextNode(), root_1);

                adaptor.addChild(root_1, stream_ACTION.nextNode());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "finallyClause"

    public static class element_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "element"
    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:229:1: element : elementNoOptionSpec ;
    public final ANTLRv3Parser.element_return element() throws RecognitionException {
        ANTLRv3Parser.element_return retval = new ANTLRv3Parser.element_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        ANTLRv3Parser.elementNoOptionSpec_return elementNoOptionSpec92 = null;



        try {
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:230:2: ( elementNoOptionSpec )
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:230:4: elementNoOptionSpec
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_elementNoOptionSpec_in_element1485);
            elementNoOptionSpec92=elementNoOptionSpec();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, elementNoOptionSpec92.getTree());

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "element"

    public static class elementNoOptionSpec_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "elementNoOptionSpec"
    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:233:1: elementNoOptionSpec : ( id (labelOp= '=' | labelOp= '+=' ) atom ( ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] ^( $labelOp id atom ) EOA[\"EOA\"] ) EOB[\"EOB\"] ) ) | -> ^( $labelOp id atom ) ) | id (labelOp= '=' | labelOp= '+=' ) block ( ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] ^( $labelOp id block ) EOA[\"EOA\"] ) EOB[\"EOB\"] ) ) | -> ^( $labelOp id block ) ) | atom ( ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] atom EOA[\"EOA\"] ) EOB[\"EOB\"] ) ) | -> atom ) | ebnf | ACTION | SEMPRED ( '=>' -> GATED_SEMPRED | -> SEMPRED ) | treeSpec ( ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] treeSpec EOA[\"EOA\"] ) EOB[\"EOB\"] ) ) | -> treeSpec ) );
    public final ANTLRv3Parser.elementNoOptionSpec_return elementNoOptionSpec() throws RecognitionException {
        ANTLRv3Parser.elementNoOptionSpec_return retval = new ANTLRv3Parser.elementNoOptionSpec_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token labelOp=null;
        Token ACTION102=null;
        Token SEMPRED103=null;
        Token string_literal104=null;
        ANTLRv3Parser.id_return id93 = null;

        ANTLRv3Parser.atom_return atom94 = null;

        ANTLRv3Parser.ebnfSuffix_return ebnfSuffix95 = null;

        ANTLRv3Parser.id_return id96 = null;

        ANTLRv3Parser.block_return block97 = null;

        ANTLRv3Parser.ebnfSuffix_return ebnfSuffix98 = null;

        ANTLRv3Parser.atom_return atom99 = null;

        ANTLRv3Parser.ebnfSuffix_return ebnfSuffix100 = null;

        ANTLRv3Parser.ebnf_return ebnf101 = null;

        ANTLRv3Parser.treeSpec_return treeSpec105 = null;

        ANTLRv3Parser.ebnfSuffix_return ebnfSuffix106 = null;


        CommonTree labelOp_tree=null;
        CommonTree ACTION102_tree=null;
        CommonTree SEMPRED103_tree=null;
        CommonTree string_literal104_tree=null;
        RewriteRuleTokenStream stream_SEMPRED=new RewriteRuleTokenStream(adaptor,"token SEMPRED");
        RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
        RewriteRuleTokenStream stream_87=new RewriteRuleTokenStream(adaptor,"token 87");
        RewriteRuleTokenStream stream_88=new RewriteRuleTokenStream(adaptor,"token 88");
        RewriteRuleSubtreeStream stream_id=new RewriteRuleSubtreeStream(adaptor,"rule id");
        RewriteRuleSubtreeStream stream_atom=new RewriteRuleSubtreeStream(adaptor,"rule atom");
        RewriteRuleSubtreeStream stream_ebnfSuffix=new RewriteRuleSubtreeStream(adaptor,"rule ebnfSuffix");
        RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");
        RewriteRuleSubtreeStream stream_treeSpec=new RewriteRuleSubtreeStream(adaptor,"rule treeSpec");
        try {
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:234:2: ( id (labelOp= '=' | labelOp= '+=' ) atom ( ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] ^( $labelOp id atom ) EOA[\"EOA\"] ) EOB[\"EOB\"] ) ) | -> ^( $labelOp id atom ) ) | id (labelOp= '=' | labelOp= '+=' ) block ( ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] ^( $labelOp id block ) EOA[\"EOA\"] ) EOB[\"EOB\"] ) ) | -> ^( $labelOp id block ) ) | atom ( ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] atom EOA[\"EOA\"] ) EOB[\"EOB\"] ) ) | -> atom ) | ebnf | ACTION | SEMPRED ( '=>' -> GATED_SEMPRED | -> SEMPRED ) | treeSpec ( ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] treeSpec EOA[\"EOA\"] ) EOB[\"EOB\"] ) ) | -> treeSpec ) )
            int alt46=7;
            alt46 = dfa46.predict(input);
            switch (alt46) {
                case 1 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:234:4: id (labelOp= '=' | labelOp= '+=' ) atom ( ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] ^( $labelOp id atom ) EOA[\"EOA\"] ) EOB[\"EOB\"] ) ) | -> ^( $labelOp id atom ) )
                    {
                    pushFollow(FOLLOW_id_in_elementNoOptionSpec1496);
                    id93=id();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_id.add(id93.getTree());
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:234:7: (labelOp= '=' | labelOp= '+=' )
                    int alt39=2;
                    int LA39_0 = input.LA(1);

                    if ( (LA39_0==71) ) {
                        alt39=1;
                    }
                    else if ( (LA39_0==87) ) {
                        alt39=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 39, 0, input);

                        throw nvae;
                    }
                    switch (alt39) {
                        case 1 :
                            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:234:8: labelOp= '='
                            {
                            labelOp=(Token)match(input,71,FOLLOW_71_in_elementNoOptionSpec1501); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_71.add(labelOp);


                            }
                            break;
                        case 2 :
                            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:234:20: labelOp= '+='
                            {
                            labelOp=(Token)match(input,87,FOLLOW_87_in_elementNoOptionSpec1505); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_87.add(labelOp);


                            }
                            break;

                    }

                    pushFollow(FOLLOW_atom_in_elementNoOptionSpec1508);
                    atom94=atom();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_atom.add(atom94.getTree());
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:235:3: ( ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] ^( $labelOp id atom ) EOA[\"EOA\"] ) EOB[\"EOB\"] ) ) | -> ^( $labelOp id atom ) )
                    int alt40=2;
                    int LA40_0 = input.LA(1);

                    if ( (LA40_0==74||(LA40_0>=90 && LA40_0<=91)) ) {
                        alt40=1;
                    }
                    else if ( (LA40_0==SEMPRED||LA40_0==TREE_BEGIN||LA40_0==REWRITE||(LA40_0>=TOKEN_REF && LA40_0<=ACTION)||LA40_0==RULE_REF||LA40_0==69||(LA40_0>=82 && LA40_0<=84)||LA40_0==89||LA40_0==92) ) {
                        alt40=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 40, 0, input);

                        throw nvae;
                    }
                    switch (alt40) {
                        case 1 :
                            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:235:5: ebnfSuffix
                            {
                            pushFollow(FOLLOW_ebnfSuffix_in_elementNoOptionSpec1514);
                            ebnfSuffix95=ebnfSuffix();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_ebnfSuffix.add(ebnfSuffix95.getTree());


                            // AST REWRITE
                            // elements: ebnfSuffix, labelOp, atom, id
                            // token labels: labelOp
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleTokenStream stream_labelOp=new RewriteRuleTokenStream(adaptor,"token labelOp",labelOp);
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 235:16: -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] ^( $labelOp id atom ) EOA[\"EOA\"] ) EOB[\"EOB\"] ) )
                            {
                                // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:235:19: ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] ^( $labelOp id atom ) EOA[\"EOA\"] ) EOB[\"EOB\"] ) )
                                {
                                CommonTree root_1 = (CommonTree)adaptor.nil();
                                root_1 = (CommonTree)adaptor.becomeRoot(stream_ebnfSuffix.nextNode(), root_1);

                                // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:235:33: ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] ^( $labelOp id atom ) EOA[\"EOA\"] ) EOB[\"EOB\"] )
                                {
                                CommonTree root_2 = (CommonTree)adaptor.nil();
                                root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(BLOCK, "BLOCK"), root_2);

                                // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:235:50: ^( ALT[\"ALT\"] ^( $labelOp id atom ) EOA[\"EOA\"] )
                                {
                                CommonTree root_3 = (CommonTree)adaptor.nil();
                                root_3 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ALT, "ALT"), root_3);

                                // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:235:63: ^( $labelOp id atom )
                                {
                                CommonTree root_4 = (CommonTree)adaptor.nil();
                                root_4 = (CommonTree)adaptor.becomeRoot(stream_labelOp.nextNode(), root_4);

                                adaptor.addChild(root_4, stream_id.nextTree());
                                adaptor.addChild(root_4, stream_atom.nextTree());

                                adaptor.addChild(root_3, root_4);
                                }
                                adaptor.addChild(root_3, (CommonTree)adaptor.create(EOA, "EOA"));

                                adaptor.addChild(root_2, root_3);
                                }
                                adaptor.addChild(root_2, (CommonTree)adaptor.create(EOB, "EOB"));

                                adaptor.addChild(root_1, root_2);
                                }

                                adaptor.addChild(root_0, root_1);
                                }

                            }

                            retval.tree = root_0;}
                            }
                            break;
                        case 2 :
                            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:236:8: 
                            {

                            // AST REWRITE
                            // elements: id, labelOp, atom
                            // token labels: labelOp
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleTokenStream stream_labelOp=new RewriteRuleTokenStream(adaptor,"token labelOp",labelOp);
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 236:8: -> ^( $labelOp id atom )
                            {
                                // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:236:11: ^( $labelOp id atom )
                                {
                                CommonTree root_1 = (CommonTree)adaptor.nil();
                                root_1 = (CommonTree)adaptor.becomeRoot(stream_labelOp.nextNode(), root_1);

                                adaptor.addChild(root_1, stream_id.nextTree());
                                adaptor.addChild(root_1, stream_atom.nextTree());

                                adaptor.addChild(root_0, root_1);
                                }

                            }

                            retval.tree = root_0;}
                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:238:4: id (labelOp= '=' | labelOp= '+=' ) block ( ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] ^( $labelOp id block ) EOA[\"EOA\"] ) EOB[\"EOB\"] ) ) | -> ^( $labelOp id block ) )
                    {
                    pushFollow(FOLLOW_id_in_elementNoOptionSpec1573);
                    id96=id();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_id.add(id96.getTree());
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:238:7: (labelOp= '=' | labelOp= '+=' )
                    int alt41=2;
                    int LA41_0 = input.LA(1);

                    if ( (LA41_0==71) ) {
                        alt41=1;
                    }
                    else if ( (LA41_0==87) ) {
                        alt41=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 41, 0, input);

                        throw nvae;
                    }
                    switch (alt41) {
                        case 1 :
                            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:238:8: labelOp= '='
                            {
                            labelOp=(Token)match(input,71,FOLLOW_71_in_elementNoOptionSpec1578); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_71.add(labelOp);


                            }
                            break;
                        case 2 :
                            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:238:20: labelOp= '+='
                            {
                            labelOp=(Token)match(input,87,FOLLOW_87_in_elementNoOptionSpec1582); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_87.add(labelOp);


                            }
                            break;

                    }

                    pushFollow(FOLLOW_block_in_elementNoOptionSpec1585);
                    block97=block();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_block.add(block97.getTree());
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:239:3: ( ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] ^( $labelOp id block ) EOA[\"EOA\"] ) EOB[\"EOB\"] ) ) | -> ^( $labelOp id block ) )
                    int alt42=2;
                    int LA42_0 = input.LA(1);

                    if ( (LA42_0==74||(LA42_0>=90 && LA42_0<=91)) ) {
                        alt42=1;
                    }
                    else if ( (LA42_0==SEMPRED||LA42_0==TREE_BEGIN||LA42_0==REWRITE||(LA42_0>=TOKEN_REF && LA42_0<=ACTION)||LA42_0==RULE_REF||LA42_0==69||(LA42_0>=82 && LA42_0<=84)||LA42_0==89||LA42_0==92) ) {
                        alt42=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 42, 0, input);

                        throw nvae;
                    }
                    switch (alt42) {
                        case 1 :
                            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:239:5: ebnfSuffix
                            {
                            pushFollow(FOLLOW_ebnfSuffix_in_elementNoOptionSpec1591);
                            ebnfSuffix98=ebnfSuffix();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_ebnfSuffix.add(ebnfSuffix98.getTree());


                            // AST REWRITE
                            // elements: ebnfSuffix, id, labelOp, block
                            // token labels: labelOp
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleTokenStream stream_labelOp=new RewriteRuleTokenStream(adaptor,"token labelOp",labelOp);
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 239:16: -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] ^( $labelOp id block ) EOA[\"EOA\"] ) EOB[\"EOB\"] ) )
                            {
                                // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:239:19: ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] ^( $labelOp id block ) EOA[\"EOA\"] ) EOB[\"EOB\"] ) )
                                {
                                CommonTree root_1 = (CommonTree)adaptor.nil();
                                root_1 = (CommonTree)adaptor.becomeRoot(stream_ebnfSuffix.nextNode(), root_1);

                                // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:239:33: ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] ^( $labelOp id block ) EOA[\"EOA\"] ) EOB[\"EOB\"] )
                                {
                                CommonTree root_2 = (CommonTree)adaptor.nil();
                                root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(BLOCK, "BLOCK"), root_2);

                                // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:239:50: ^( ALT[\"ALT\"] ^( $labelOp id block ) EOA[\"EOA\"] )
                                {
                                CommonTree root_3 = (CommonTree)adaptor.nil();
                                root_3 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ALT, "ALT"), root_3);

                                // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:239:63: ^( $labelOp id block )
                                {
                                CommonTree root_4 = (CommonTree)adaptor.nil();
                                root_4 = (CommonTree)adaptor.becomeRoot(stream_labelOp.nextNode(), root_4);

                                adaptor.addChild(root_4, stream_id.nextTree());
                                adaptor.addChild(root_4, stream_block.nextTree());

                                adaptor.addChild(root_3, root_4);
                                }
                                adaptor.addChild(root_3, (CommonTree)adaptor.create(EOA, "EOA"));

                                adaptor.addChild(root_2, root_3);
                                }
                                adaptor.addChild(root_2, (CommonTree)adaptor.create(EOB, "EOB"));

                                adaptor.addChild(root_1, root_2);
                                }

                                adaptor.addChild(root_0, root_1);
                                }

                            }

                            retval.tree = root_0;}
                            }
                            break;
                        case 2 :
                            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:240:8: 
                            {

                            // AST REWRITE
                            // elements: labelOp, id, block
                            // token labels: labelOp
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleTokenStream stream_labelOp=new RewriteRuleTokenStream(adaptor,"token labelOp",labelOp);
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 240:8: -> ^( $labelOp id block )
                            {
                                // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:240:11: ^( $labelOp id block )
                                {
                                CommonTree root_1 = (CommonTree)adaptor.nil();
                                root_1 = (CommonTree)adaptor.becomeRoot(stream_labelOp.nextNode(), root_1);

                                adaptor.addChild(root_1, stream_id.nextTree());
                                adaptor.addChild(root_1, stream_block.nextTree());

                                adaptor.addChild(root_0, root_1);
                                }

                            }

                            retval.tree = root_0;}
                            }
                            break;

                    }


                    }
                    break;
                case 3 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:242:4: atom ( ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] atom EOA[\"EOA\"] ) EOB[\"EOB\"] ) ) | -> atom )
                    {
                    pushFollow(FOLLOW_atom_in_elementNoOptionSpec1650);
                    atom99=atom();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_atom.add(atom99.getTree());
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:243:3: ( ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] atom EOA[\"EOA\"] ) EOB[\"EOB\"] ) ) | -> atom )
                    int alt43=2;
                    int LA43_0 = input.LA(1);

                    if ( (LA43_0==74||(LA43_0>=90 && LA43_0<=91)) ) {
                        alt43=1;
                    }
                    else if ( (LA43_0==SEMPRED||LA43_0==TREE_BEGIN||LA43_0==REWRITE||(LA43_0>=TOKEN_REF && LA43_0<=ACTION)||LA43_0==RULE_REF||LA43_0==69||(LA43_0>=82 && LA43_0<=84)||LA43_0==89||LA43_0==92) ) {
                        alt43=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 43, 0, input);

                        throw nvae;
                    }
                    switch (alt43) {
                        case 1 :
                            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:243:5: ebnfSuffix
                            {
                            pushFollow(FOLLOW_ebnfSuffix_in_elementNoOptionSpec1656);
                            ebnfSuffix100=ebnfSuffix();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_ebnfSuffix.add(ebnfSuffix100.getTree());


                            // AST REWRITE
                            // elements: atom, ebnfSuffix
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 243:16: -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] atom EOA[\"EOA\"] ) EOB[\"EOB\"] ) )
                            {
                                // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:243:19: ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] atom EOA[\"EOA\"] ) EOB[\"EOB\"] ) )
                                {
                                CommonTree root_1 = (CommonTree)adaptor.nil();
                                root_1 = (CommonTree)adaptor.becomeRoot(stream_ebnfSuffix.nextNode(), root_1);

                                // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:243:33: ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] atom EOA[\"EOA\"] ) EOB[\"EOB\"] )
                                {
                                CommonTree root_2 = (CommonTree)adaptor.nil();
                                root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(BLOCK, "BLOCK"), root_2);

                                // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:243:50: ^( ALT[\"ALT\"] atom EOA[\"EOA\"] )
                                {
                                CommonTree root_3 = (CommonTree)adaptor.nil();
                                root_3 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ALT, "ALT"), root_3);

                                adaptor.addChild(root_3, stream_atom.nextTree());
                                adaptor.addChild(root_3, (CommonTree)adaptor.create(EOA, "EOA"));

                                adaptor.addChild(root_2, root_3);
                                }
                                adaptor.addChild(root_2, (CommonTree)adaptor.create(EOB, "EOB"));

                                adaptor.addChild(root_1, root_2);
                                }

                                adaptor.addChild(root_0, root_1);
                                }

                            }

                            retval.tree = root_0;}
                            }
                            break;
                        case 2 :
                            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:244:8: 
                            {

                            // AST REWRITE
                            // elements: atom
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 244:8: -> atom
                            {
                                adaptor.addChild(root_0, stream_atom.nextTree());

                            }

                            retval.tree = root_0;}
                            }
                            break;

                    }


                    }
                    break;
                case 4 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:246:4: ebnf
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_ebnf_in_elementNoOptionSpec1702);
                    ebnf101=ebnf();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, ebnf101.getTree());

                    }
                    break;
                case 5 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:247:6: ACTION
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    ACTION102=(Token)match(input,ACTION,FOLLOW_ACTION_in_elementNoOptionSpec1709); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ACTION102_tree = (CommonTree)adaptor.create(ACTION102);
                    adaptor.addChild(root_0, ACTION102_tree);
                    }

                    }
                    break;
                case 6 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:248:6: SEMPRED ( '=>' -> GATED_SEMPRED | -> SEMPRED )
                    {
                    SEMPRED103=(Token)match(input,SEMPRED,FOLLOW_SEMPRED_in_elementNoOptionSpec1716); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_SEMPRED.add(SEMPRED103);

                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:248:14: ( '=>' -> GATED_SEMPRED | -> SEMPRED )
                    int alt44=2;
                    int LA44_0 = input.LA(1);

                    if ( (LA44_0==88) ) {
                        alt44=1;
                    }
                    else if ( (LA44_0==SEMPRED||LA44_0==TREE_BEGIN||LA44_0==REWRITE||(LA44_0>=TOKEN_REF && LA44_0<=ACTION)||LA44_0==RULE_REF||LA44_0==69||(LA44_0>=82 && LA44_0<=84)||LA44_0==89||LA44_0==92) ) {
                        alt44=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 44, 0, input);

                        throw nvae;
                    }
                    switch (alt44) {
                        case 1 :
                            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:248:16: '=>'
                            {
                            string_literal104=(Token)match(input,88,FOLLOW_88_in_elementNoOptionSpec1720); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_88.add(string_literal104);



                            // AST REWRITE
                            // elements: 
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 248:21: -> GATED_SEMPRED
                            {
                                adaptor.addChild(root_0, (CommonTree)adaptor.create(GATED_SEMPRED, "GATED_SEMPRED"));

                            }

                            retval.tree = root_0;}
                            }
                            break;
                        case 2 :
                            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:248:40: 
                            {

                            // AST REWRITE
                            // elements: SEMPRED
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 248:40: -> SEMPRED
                            {
                                adaptor.addChild(root_0, stream_SEMPRED.nextNode());

                            }

                            retval.tree = root_0;}
                            }
                            break;

                    }


                    }
                    break;
                case 7 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:249:6: treeSpec ( ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] treeSpec EOA[\"EOA\"] ) EOB[\"EOB\"] ) ) | -> treeSpec )
                    {
                    pushFollow(FOLLOW_treeSpec_in_elementNoOptionSpec1739);
                    treeSpec105=treeSpec();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_treeSpec.add(treeSpec105.getTree());
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:250:3: ( ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] treeSpec EOA[\"EOA\"] ) EOB[\"EOB\"] ) ) | -> treeSpec )
                    int alt45=2;
                    int LA45_0 = input.LA(1);

                    if ( (LA45_0==74||(LA45_0>=90 && LA45_0<=91)) ) {
                        alt45=1;
                    }
                    else if ( (LA45_0==SEMPRED||LA45_0==TREE_BEGIN||LA45_0==REWRITE||(LA45_0>=TOKEN_REF && LA45_0<=ACTION)||LA45_0==RULE_REF||LA45_0==69||(LA45_0>=82 && LA45_0<=84)||LA45_0==89||LA45_0==92) ) {
                        alt45=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 45, 0, input);

                        throw nvae;
                    }
                    switch (alt45) {
                        case 1 :
                            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:250:5: ebnfSuffix
                            {
                            pushFollow(FOLLOW_ebnfSuffix_in_elementNoOptionSpec1745);
                            ebnfSuffix106=ebnfSuffix();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_ebnfSuffix.add(ebnfSuffix106.getTree());


                            // AST REWRITE
                            // elements: treeSpec, ebnfSuffix
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 250:16: -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] treeSpec EOA[\"EOA\"] ) EOB[\"EOB\"] ) )
                            {
                                // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:250:19: ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] treeSpec EOA[\"EOA\"] ) EOB[\"EOB\"] ) )
                                {
                                CommonTree root_1 = (CommonTree)adaptor.nil();
                                root_1 = (CommonTree)adaptor.becomeRoot(stream_ebnfSuffix.nextNode(), root_1);

                                // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:250:33: ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] treeSpec EOA[\"EOA\"] ) EOB[\"EOB\"] )
                                {
                                CommonTree root_2 = (CommonTree)adaptor.nil();
                                root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(BLOCK, "BLOCK"), root_2);

                                // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:250:50: ^( ALT[\"ALT\"] treeSpec EOA[\"EOA\"] )
                                {
                                CommonTree root_3 = (CommonTree)adaptor.nil();
                                root_3 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ALT, "ALT"), root_3);

                                adaptor.addChild(root_3, stream_treeSpec.nextTree());
                                adaptor.addChild(root_3, (CommonTree)adaptor.create(EOA, "EOA"));

                                adaptor.addChild(root_2, root_3);
                                }
                                adaptor.addChild(root_2, (CommonTree)adaptor.create(EOB, "EOB"));

                                adaptor.addChild(root_1, root_2);
                                }

                                adaptor.addChild(root_0, root_1);
                                }

                            }

                            retval.tree = root_0;}
                            }
                            break;
                        case 2 :
                            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:251:8: 
                            {

                            // AST REWRITE
                            // elements: treeSpec
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 251:8: -> treeSpec
                            {
                                adaptor.addChild(root_0, stream_treeSpec.nextTree());

                            }

                            retval.tree = root_0;}
                            }
                            break;

                    }


                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "elementNoOptionSpec"

    public static class atom_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "atom"
    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:255:1: atom : ( range ( (op= '^' | op= '!' ) -> ^( $op range ) | -> range ) | terminal | notSet ( (op= '^' | op= '!' ) -> ^( $op notSet ) | -> notSet ) | RULE_REF (arg= ARG_ACTION )? ( (op= '^' | op= '!' ) )? -> {$arg!=null&&op!=null}? ^( $op RULE_REF $arg) -> {$arg!=null}? ^( RULE_REF $arg) -> {$op!=null}? ^( $op RULE_REF ) -> RULE_REF );
    public final ANTLRv3Parser.atom_return atom() throws RecognitionException {
        ANTLRv3Parser.atom_return retval = new ANTLRv3Parser.atom_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token op=null;
        Token arg=null;
        Token RULE_REF110=null;
        ANTLRv3Parser.range_return range107 = null;

        ANTLRv3Parser.terminal_return terminal108 = null;

        ANTLRv3Parser.notSet_return notSet109 = null;


        CommonTree op_tree=null;
        CommonTree arg_tree=null;
        CommonTree RULE_REF110_tree=null;
        RewriteRuleTokenStream stream_BANG=new RewriteRuleTokenStream(adaptor,"token BANG");
        RewriteRuleTokenStream stream_ROOT=new RewriteRuleTokenStream(adaptor,"token ROOT");
        RewriteRuleTokenStream stream_RULE_REF=new RewriteRuleTokenStream(adaptor,"token RULE_REF");
        RewriteRuleTokenStream stream_ARG_ACTION=new RewriteRuleTokenStream(adaptor,"token ARG_ACTION");
        RewriteRuleSubtreeStream stream_range=new RewriteRuleSubtreeStream(adaptor,"rule range");
        RewriteRuleSubtreeStream stream_notSet=new RewriteRuleSubtreeStream(adaptor,"rule notSet");
        try {
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:255:5: ( range ( (op= '^' | op= '!' ) -> ^( $op range ) | -> range ) | terminal | notSet ( (op= '^' | op= '!' ) -> ^( $op notSet ) | -> notSet ) | RULE_REF (arg= ARG_ACTION )? ( (op= '^' | op= '!' ) )? -> {$arg!=null&&op!=null}? ^( $op RULE_REF $arg) -> {$arg!=null}? ^( RULE_REF $arg) -> {$op!=null}? ^( $op RULE_REF ) -> RULE_REF )
            int alt54=4;
            switch ( input.LA(1) ) {
            case CHAR_LITERAL:
                {
                int LA54_1 = input.LA(2);

                if ( (LA54_1==RANGE) ) {
                    alt54=1;
                }
                else if ( (LA54_1==SEMPRED||(LA54_1>=TREE_BEGIN && LA54_1<=REWRITE)||(LA54_1>=TOKEN_REF && LA54_1<=ACTION)||LA54_1==RULE_REF||LA54_1==69||LA54_1==74||(LA54_1>=82 && LA54_1<=84)||(LA54_1>=89 && LA54_1<=92)) ) {
                    alt54=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 54, 1, input);

                    throw nvae;
                }
                }
                break;
            case TOKEN_REF:
            case STRING_LITERAL:
            case 92:
                {
                alt54=2;
                }
                break;
            case 89:
                {
                alt54=3;
                }
                break;
            case RULE_REF:
                {
                alt54=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 54, 0, input);

                throw nvae;
            }

            switch (alt54) {
                case 1 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:255:9: range ( (op= '^' | op= '!' ) -> ^( $op range ) | -> range )
                    {
                    pushFollow(FOLLOW_range_in_atom1797);
                    range107=range();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_range.add(range107.getTree());
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:255:15: ( (op= '^' | op= '!' ) -> ^( $op range ) | -> range )
                    int alt48=2;
                    int LA48_0 = input.LA(1);

                    if ( ((LA48_0>=ROOT && LA48_0<=BANG)) ) {
                        alt48=1;
                    }
                    else if ( (LA48_0==SEMPRED||LA48_0==TREE_BEGIN||LA48_0==REWRITE||(LA48_0>=TOKEN_REF && LA48_0<=ACTION)||LA48_0==RULE_REF||LA48_0==69||LA48_0==74||(LA48_0>=82 && LA48_0<=84)||(LA48_0>=89 && LA48_0<=92)) ) {
                        alt48=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 48, 0, input);

                        throw nvae;
                    }
                    switch (alt48) {
                        case 1 :
                            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:255:17: (op= '^' | op= '!' )
                            {
                            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:255:17: (op= '^' | op= '!' )
                            int alt47=2;
                            int LA47_0 = input.LA(1);

                            if ( (LA47_0==ROOT) ) {
                                alt47=1;
                            }
                            else if ( (LA47_0==BANG) ) {
                                alt47=2;
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return retval;}
                                NoViableAltException nvae =
                                    new NoViableAltException("", 47, 0, input);

                                throw nvae;
                            }
                            switch (alt47) {
                                case 1 :
                                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:255:18: op= '^'
                                    {
                                    op=(Token)match(input,ROOT,FOLLOW_ROOT_in_atom1804); if (state.failed) return retval; 
                                    if ( state.backtracking==0 ) stream_ROOT.add(op);


                                    }
                                    break;
                                case 2 :
                                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:255:25: op= '!'
                                    {
                                    op=(Token)match(input,BANG,FOLLOW_BANG_in_atom1808); if (state.failed) return retval; 
                                    if ( state.backtracking==0 ) stream_BANG.add(op);


                                    }
                                    break;

                            }



                            // AST REWRITE
                            // elements: range, op
                            // token labels: op
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleTokenStream stream_op=new RewriteRuleTokenStream(adaptor,"token op",op);
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 255:33: -> ^( $op range )
                            {
                                // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:255:36: ^( $op range )
                                {
                                CommonTree root_1 = (CommonTree)adaptor.nil();
                                root_1 = (CommonTree)adaptor.becomeRoot(stream_op.nextNode(), root_1);

                                adaptor.addChild(root_1, stream_range.nextTree());

                                adaptor.addChild(root_0, root_1);
                                }

                            }

                            retval.tree = root_0;}
                            }
                            break;
                        case 2 :
                            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:255:51: 
                            {

                            // AST REWRITE
                            // elements: range
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 255:51: -> range
                            {
                                adaptor.addChild(root_0, stream_range.nextTree());

                            }

                            retval.tree = root_0;}
                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:256:9: terminal
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_terminal_in_atom1836);
                    terminal108=terminal();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, terminal108.getTree());

                    }
                    break;
                case 3 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:257:7: notSet ( (op= '^' | op= '!' ) -> ^( $op notSet ) | -> notSet )
                    {
                    pushFollow(FOLLOW_notSet_in_atom1844);
                    notSet109=notSet();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_notSet.add(notSet109.getTree());
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:257:14: ( (op= '^' | op= '!' ) -> ^( $op notSet ) | -> notSet )
                    int alt50=2;
                    int LA50_0 = input.LA(1);

                    if ( ((LA50_0>=ROOT && LA50_0<=BANG)) ) {
                        alt50=1;
                    }
                    else if ( (LA50_0==SEMPRED||LA50_0==TREE_BEGIN||LA50_0==REWRITE||(LA50_0>=TOKEN_REF && LA50_0<=ACTION)||LA50_0==RULE_REF||LA50_0==69||LA50_0==74||(LA50_0>=82 && LA50_0<=84)||(LA50_0>=89 && LA50_0<=92)) ) {
                        alt50=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 50, 0, input);

                        throw nvae;
                    }
                    switch (alt50) {
                        case 1 :
                            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:257:16: (op= '^' | op= '!' )
                            {
                            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:257:16: (op= '^' | op= '!' )
                            int alt49=2;
                            int LA49_0 = input.LA(1);

                            if ( (LA49_0==ROOT) ) {
                                alt49=1;
                            }
                            else if ( (LA49_0==BANG) ) {
                                alt49=2;
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return retval;}
                                NoViableAltException nvae =
                                    new NoViableAltException("", 49, 0, input);

                                throw nvae;
                            }
                            switch (alt49) {
                                case 1 :
                                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:257:17: op= '^'
                                    {
                                    op=(Token)match(input,ROOT,FOLLOW_ROOT_in_atom1851); if (state.failed) return retval; 
                                    if ( state.backtracking==0 ) stream_ROOT.add(op);


                                    }
                                    break;
                                case 2 :
                                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:257:24: op= '!'
                                    {
                                    op=(Token)match(input,BANG,FOLLOW_BANG_in_atom1855); if (state.failed) return retval; 
                                    if ( state.backtracking==0 ) stream_BANG.add(op);


                                    }
                                    break;

                            }



                            // AST REWRITE
                            // elements: op, notSet
                            // token labels: op
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleTokenStream stream_op=new RewriteRuleTokenStream(adaptor,"token op",op);
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 257:32: -> ^( $op notSet )
                            {
                                // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:257:35: ^( $op notSet )
                                {
                                CommonTree root_1 = (CommonTree)adaptor.nil();
                                root_1 = (CommonTree)adaptor.becomeRoot(stream_op.nextNode(), root_1);

                                adaptor.addChild(root_1, stream_notSet.nextTree());

                                adaptor.addChild(root_0, root_1);
                                }

                            }

                            retval.tree = root_0;}
                            }
                            break;
                        case 2 :
                            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:257:51: 
                            {

                            // AST REWRITE
                            // elements: notSet
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 257:51: -> notSet
                            {
                                adaptor.addChild(root_0, stream_notSet.nextTree());

                            }

                            retval.tree = root_0;}
                            }
                            break;

                    }


                    }
                    break;
                case 4 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:258:9: RULE_REF (arg= ARG_ACTION )? ( (op= '^' | op= '!' ) )?
                    {
                    RULE_REF110=(Token)match(input,RULE_REF,FOLLOW_RULE_REF_in_atom1883); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RULE_REF.add(RULE_REF110);

                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:258:18: (arg= ARG_ACTION )?
                    int alt51=2;
                    int LA51_0 = input.LA(1);

                    if ( (LA51_0==ARG_ACTION) ) {
                        alt51=1;
                    }
                    switch (alt51) {
                        case 1 :
                            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:258:20: arg= ARG_ACTION
                            {
                            arg=(Token)match(input,ARG_ACTION,FOLLOW_ARG_ACTION_in_atom1889); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_ARG_ACTION.add(arg);


                            }
                            break;

                    }

                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:258:38: ( (op= '^' | op= '!' ) )?
                    int alt53=2;
                    int LA53_0 = input.LA(1);

                    if ( ((LA53_0>=ROOT && LA53_0<=BANG)) ) {
                        alt53=1;
                    }
                    switch (alt53) {
                        case 1 :
                            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:258:40: (op= '^' | op= '!' )
                            {
                            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:258:40: (op= '^' | op= '!' )
                            int alt52=2;
                            int LA52_0 = input.LA(1);

                            if ( (LA52_0==ROOT) ) {
                                alt52=1;
                            }
                            else if ( (LA52_0==BANG) ) {
                                alt52=2;
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return retval;}
                                NoViableAltException nvae =
                                    new NoViableAltException("", 52, 0, input);

                                throw nvae;
                            }
                            switch (alt52) {
                                case 1 :
                                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:258:41: op= '^'
                                    {
                                    op=(Token)match(input,ROOT,FOLLOW_ROOT_in_atom1899); if (state.failed) return retval; 
                                    if ( state.backtracking==0 ) stream_ROOT.add(op);


                                    }
                                    break;
                                case 2 :
                                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:258:48: op= '!'
                                    {
                                    op=(Token)match(input,BANG,FOLLOW_BANG_in_atom1903); if (state.failed) return retval; 
                                    if ( state.backtracking==0 ) stream_BANG.add(op);


                                    }
                                    break;

                            }


                            }
                            break;

                    }



                    // AST REWRITE
                    // elements: RULE_REF, RULE_REF, op, op, arg, arg, RULE_REF, RULE_REF
                    // token labels: arg, op
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleTokenStream stream_arg=new RewriteRuleTokenStream(adaptor,"token arg",arg);
                    RewriteRuleTokenStream stream_op=new RewriteRuleTokenStream(adaptor,"token op",op);
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 259:6: -> {$arg!=null&&op!=null}? ^( $op RULE_REF $arg)
                    if (arg!=null&&op!=null) {
                        // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:259:33: ^( $op RULE_REF $arg)
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_op.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_RULE_REF.nextNode());
                        adaptor.addChild(root_1, stream_arg.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }
                    else // 260:6: -> {$arg!=null}? ^( RULE_REF $arg)
                    if (arg!=null) {
                        // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:260:25: ^( RULE_REF $arg)
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_RULE_REF.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_arg.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }
                    else // 261:6: -> {$op!=null}? ^( $op RULE_REF )
                    if (op!=null) {
                        // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:261:25: ^( $op RULE_REF )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_op.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_RULE_REF.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }
                    else // 262:6: -> RULE_REF
                    {
                        adaptor.addChild(root_0, stream_RULE_REF.nextNode());

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "atom"

    public static class notSet_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "notSet"
    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:265:1: notSet : '~' ( notTerminal -> ^( '~' notTerminal ) | block -> ^( '~' block ) ) ;
    public final ANTLRv3Parser.notSet_return notSet() throws RecognitionException {
        ANTLRv3Parser.notSet_return retval = new ANTLRv3Parser.notSet_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal111=null;
        ANTLRv3Parser.notTerminal_return notTerminal112 = null;

        ANTLRv3Parser.block_return block113 = null;


        CommonTree char_literal111_tree=null;
        RewriteRuleTokenStream stream_89=new RewriteRuleTokenStream(adaptor,"token 89");
        RewriteRuleSubtreeStream stream_notTerminal=new RewriteRuleSubtreeStream(adaptor,"rule notTerminal");
        RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");
        try {
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:266:2: ( '~' ( notTerminal -> ^( '~' notTerminal ) | block -> ^( '~' block ) ) )
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:266:4: '~' ( notTerminal -> ^( '~' notTerminal ) | block -> ^( '~' block ) )
            {
            char_literal111=(Token)match(input,89,FOLLOW_89_in_notSet1986); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_89.add(char_literal111);

            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:267:3: ( notTerminal -> ^( '~' notTerminal ) | block -> ^( '~' block ) )
            int alt55=2;
            int LA55_0 = input.LA(1);

            if ( ((LA55_0>=TOKEN_REF && LA55_0<=CHAR_LITERAL)) ) {
                alt55=1;
            }
            else if ( (LA55_0==82) ) {
                alt55=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 55, 0, input);

                throw nvae;
            }
            switch (alt55) {
                case 1 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:267:5: notTerminal
                    {
                    pushFollow(FOLLOW_notTerminal_in_notSet1992);
                    notTerminal112=notTerminal();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_notTerminal.add(notTerminal112.getTree());


                    // AST REWRITE
                    // elements: notTerminal, 89
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 267:17: -> ^( '~' notTerminal )
                    {
                        // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:267:20: ^( '~' notTerminal )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_89.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_notTerminal.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:268:5: block
                    {
                    pushFollow(FOLLOW_block_in_notSet2006);
                    block113=block();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_block.add(block113.getTree());


                    // AST REWRITE
                    // elements: block, 89
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 268:12: -> ^( '~' block )
                    {
                        // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:268:15: ^( '~' block )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_89.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_block.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "notSet"

    public static class treeSpec_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "treeSpec"
    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:272:1: treeSpec : '^(' element ( element )+ ')' -> ^( TREE_BEGIN ( element )+ ) ;
    public final ANTLRv3Parser.treeSpec_return treeSpec() throws RecognitionException {
        ANTLRv3Parser.treeSpec_return retval = new ANTLRv3Parser.treeSpec_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal114=null;
        Token char_literal117=null;
        ANTLRv3Parser.element_return element115 = null;

        ANTLRv3Parser.element_return element116 = null;


        CommonTree string_literal114_tree=null;
        CommonTree char_literal117_tree=null;
        RewriteRuleTokenStream stream_TREE_BEGIN=new RewriteRuleTokenStream(adaptor,"token TREE_BEGIN");
        RewriteRuleTokenStream stream_84=new RewriteRuleTokenStream(adaptor,"token 84");
        RewriteRuleSubtreeStream stream_element=new RewriteRuleSubtreeStream(adaptor,"rule element");
        try {
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:273:2: ( '^(' element ( element )+ ')' -> ^( TREE_BEGIN ( element )+ ) )
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:273:4: '^(' element ( element )+ ')'
            {
            string_literal114=(Token)match(input,TREE_BEGIN,FOLLOW_TREE_BEGIN_in_treeSpec2030); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_TREE_BEGIN.add(string_literal114);

            pushFollow(FOLLOW_element_in_treeSpec2032);
            element115=element();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_element.add(element115.getTree());
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:273:17: ( element )+
            int cnt56=0;
            loop56:
            do {
                int alt56=2;
                int LA56_0 = input.LA(1);

                if ( (LA56_0==SEMPRED||LA56_0==TREE_BEGIN||(LA56_0>=TOKEN_REF && LA56_0<=ACTION)||LA56_0==RULE_REF||LA56_0==82||LA56_0==89||LA56_0==92) ) {
                    alt56=1;
                }


                switch (alt56) {
            	case 1 :
            	    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:273:19: element
            	    {
            	    pushFollow(FOLLOW_element_in_treeSpec2036);
            	    element116=element();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_element.add(element116.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt56 >= 1 ) break loop56;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(56, input);
                        throw eee;
                }
                cnt56++;
            } while (true);

            char_literal117=(Token)match(input,84,FOLLOW_84_in_treeSpec2041); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_84.add(char_literal117);



            // AST REWRITE
            // elements: element
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 273:34: -> ^( TREE_BEGIN ( element )+ )
            {
                // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:273:37: ^( TREE_BEGIN ( element )+ )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TREE_BEGIN, "TREE_BEGIN"), root_1);

                if ( !(stream_element.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_element.hasNext() ) {
                    adaptor.addChild(root_1, stream_element.nextTree());

                }
                stream_element.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "treeSpec"

    public static class ebnf_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "ebnf"
    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:276:1: ebnf : block (op= '?' -> ^( OPTIONAL[op] block ) | op= '*' -> ^( CLOSURE[op] block ) | op= '+' -> ^( POSITIVE_CLOSURE[op] block ) | '=>' -> {gtype==COMBINED_GRAMMAR &&\n\t\t\t\t\t Character.isUpperCase($rule::name.charAt(0))}? ^( SYNPRED[\"=>\"] block ) -> SYN_SEMPRED | -> block ) ;
    public final ANTLRv3Parser.ebnf_return ebnf() throws RecognitionException {
        ANTLRv3Parser.ebnf_return retval = new ANTLRv3Parser.ebnf_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token op=null;
        Token string_literal119=null;
        ANTLRv3Parser.block_return block118 = null;


        CommonTree op_tree=null;
        CommonTree string_literal119_tree=null;
        RewriteRuleTokenStream stream_91=new RewriteRuleTokenStream(adaptor,"token 91");
        RewriteRuleTokenStream stream_90=new RewriteRuleTokenStream(adaptor,"token 90");
        RewriteRuleTokenStream stream_74=new RewriteRuleTokenStream(adaptor,"token 74");
        RewriteRuleTokenStream stream_88=new RewriteRuleTokenStream(adaptor,"token 88");
        RewriteRuleSubtreeStream stream_block=new RewriteRuleSubtreeStream(adaptor,"rule block");

            Token firstToken = input.LT(1);

        try {
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:285:2: ( block (op= '?' -> ^( OPTIONAL[op] block ) | op= '*' -> ^( CLOSURE[op] block ) | op= '+' -> ^( POSITIVE_CLOSURE[op] block ) | '=>' -> {gtype==COMBINED_GRAMMAR &&\n\t\t\t\t\t Character.isUpperCase($rule::name.charAt(0))}? ^( SYNPRED[\"=>\"] block ) -> SYN_SEMPRED | -> block ) )
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:285:4: block (op= '?' -> ^( OPTIONAL[op] block ) | op= '*' -> ^( CLOSURE[op] block ) | op= '+' -> ^( POSITIVE_CLOSURE[op] block ) | '=>' -> {gtype==COMBINED_GRAMMAR &&\n\t\t\t\t\t Character.isUpperCase($rule::name.charAt(0))}? ^( SYNPRED[\"=>\"] block ) -> SYN_SEMPRED | -> block )
            {
            pushFollow(FOLLOW_block_in_ebnf2073);
            block118=block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_block.add(block118.getTree());
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:286:3: (op= '?' -> ^( OPTIONAL[op] block ) | op= '*' -> ^( CLOSURE[op] block ) | op= '+' -> ^( POSITIVE_CLOSURE[op] block ) | '=>' -> {gtype==COMBINED_GRAMMAR &&\n\t\t\t\t\t Character.isUpperCase($rule::name.charAt(0))}? ^( SYNPRED[\"=>\"] block ) -> SYN_SEMPRED | -> block )
            int alt57=5;
            switch ( input.LA(1) ) {
            case 90:
                {
                alt57=1;
                }
                break;
            case 74:
                {
                alt57=2;
                }
                break;
            case 91:
                {
                alt57=3;
                }
                break;
            case 88:
                {
                alt57=4;
                }
                break;
            case SEMPRED:
            case TREE_BEGIN:
            case REWRITE:
            case TOKEN_REF:
            case STRING_LITERAL:
            case CHAR_LITERAL:
            case ACTION:
            case RULE_REF:
            case 69:
            case 82:
            case 83:
            case 84:
            case 89:
            case 92:
                {
                alt57=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 57, 0, input);

                throw nvae;
            }

            switch (alt57) {
                case 1 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:286:5: op= '?'
                    {
                    op=(Token)match(input,90,FOLLOW_90_in_ebnf2081); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_90.add(op);



                    // AST REWRITE
                    // elements: block
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 286:12: -> ^( OPTIONAL[op] block )
                    {
                        // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:286:15: ^( OPTIONAL[op] block )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(OPTIONAL, op), root_1);

                        adaptor.addChild(root_1, stream_block.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:287:5: op= '*'
                    {
                    op=(Token)match(input,74,FOLLOW_74_in_ebnf2098); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_74.add(op);



                    // AST REWRITE
                    // elements: block
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 287:12: -> ^( CLOSURE[op] block )
                    {
                        // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:287:15: ^( CLOSURE[op] block )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CLOSURE, op), root_1);

                        adaptor.addChild(root_1, stream_block.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 3 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:288:5: op= '+'
                    {
                    op=(Token)match(input,91,FOLLOW_91_in_ebnf2115); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_91.add(op);



                    // AST REWRITE
                    // elements: block
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 288:12: -> ^( POSITIVE_CLOSURE[op] block )
                    {
                        // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:288:15: ^( POSITIVE_CLOSURE[op] block )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(POSITIVE_CLOSURE, op), root_1);

                        adaptor.addChild(root_1, stream_block.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 4 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:289:7: '=>'
                    {
                    string_literal119=(Token)match(input,88,FOLLOW_88_in_ebnf2132); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_88.add(string_literal119);



                    // AST REWRITE
                    // elements: block
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 290:6: -> {gtype==COMBINED_GRAMMAR &&\n\t\t\t\t\t Character.isUpperCase($rule::name.charAt(0))}? ^( SYNPRED[\"=>\"] block )
                    if (gtype==COMBINED_GRAMMAR &&
                    					    Character.isUpperCase(((rule_scope)rule_stack.peek()).name.charAt(0))) {
                        // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:293:9: ^( SYNPRED[\"=>\"] block )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(SYNPRED, "=>"), root_1);

                        adaptor.addChild(root_1, stream_block.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }
                    else // 295:6: -> SYN_SEMPRED
                    {
                        adaptor.addChild(root_0, (CommonTree)adaptor.create(SYN_SEMPRED, "SYN_SEMPRED"));

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 5 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:296:13: 
                    {

                    // AST REWRITE
                    // elements: block
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 296:13: -> block
                    {
                        adaptor.addChild(root_0, stream_block.nextTree());

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

              	((CommonTree)retval.tree).getToken().setLine(firstToken.getLine());
              	((CommonTree)retval.tree).getToken().setCharPositionInLine(firstToken.getCharPositionInLine());

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "ebnf"

    public static class range_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "range"
    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:300:1: range : c1= CHAR_LITERAL RANGE c2= CHAR_LITERAL -> ^( CHAR_RANGE[$c1,\"..\"] $c1 $c2) ;
    public final ANTLRv3Parser.range_return range() throws RecognitionException {
        ANTLRv3Parser.range_return retval = new ANTLRv3Parser.range_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token c1=null;
        Token c2=null;
        Token RANGE120=null;

        CommonTree c1_tree=null;
        CommonTree c2_tree=null;
        CommonTree RANGE120_tree=null;
        RewriteRuleTokenStream stream_RANGE=new RewriteRuleTokenStream(adaptor,"token RANGE");
        RewriteRuleTokenStream stream_CHAR_LITERAL=new RewriteRuleTokenStream(adaptor,"token CHAR_LITERAL");

        try {
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:301:2: (c1= CHAR_LITERAL RANGE c2= CHAR_LITERAL -> ^( CHAR_RANGE[$c1,\"..\"] $c1 $c2) )
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:301:4: c1= CHAR_LITERAL RANGE c2= CHAR_LITERAL
            {
            c1=(Token)match(input,CHAR_LITERAL,FOLLOW_CHAR_LITERAL_in_range2215); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CHAR_LITERAL.add(c1);

            RANGE120=(Token)match(input,RANGE,FOLLOW_RANGE_in_range2217); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_RANGE.add(RANGE120);

            c2=(Token)match(input,CHAR_LITERAL,FOLLOW_CHAR_LITERAL_in_range2221); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_CHAR_LITERAL.add(c2);



            // AST REWRITE
            // elements: c2, c1
            // token labels: c1, c2
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleTokenStream stream_c1=new RewriteRuleTokenStream(adaptor,"token c1",c1);
            RewriteRuleTokenStream stream_c2=new RewriteRuleTokenStream(adaptor,"token c2",c2);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 301:42: -> ^( CHAR_RANGE[$c1,\"..\"] $c1 $c2)
            {
                // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:301:45: ^( CHAR_RANGE[$c1,\"..\"] $c1 $c2)
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(CHAR_RANGE, c1, ".."), root_1);

                adaptor.addChild(root_1, stream_c1.nextNode());
                adaptor.addChild(root_1, stream_c2.nextNode());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "range"

    public static class terminal_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "terminal"
    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:304:1: terminal : ( CHAR_LITERAL -> CHAR_LITERAL | TOKEN_REF ( ARG_ACTION -> ^( TOKEN_REF ARG_ACTION ) | -> TOKEN_REF ) | STRING_LITERAL -> STRING_LITERAL | '.' -> '.' ) ( '^' -> ^( '^' $terminal) | '!' -> ^( '!' $terminal) )? ;
    public final ANTLRv3Parser.terminal_return terminal() throws RecognitionException {
        ANTLRv3Parser.terminal_return retval = new ANTLRv3Parser.terminal_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token CHAR_LITERAL121=null;
        Token TOKEN_REF122=null;
        Token ARG_ACTION123=null;
        Token STRING_LITERAL124=null;
        Token char_literal125=null;
        Token char_literal126=null;
        Token char_literal127=null;

        CommonTree CHAR_LITERAL121_tree=null;
        CommonTree TOKEN_REF122_tree=null;
        CommonTree ARG_ACTION123_tree=null;
        CommonTree STRING_LITERAL124_tree=null;
        CommonTree char_literal125_tree=null;
        CommonTree char_literal126_tree=null;
        CommonTree char_literal127_tree=null;
        RewriteRuleTokenStream stream_STRING_LITERAL=new RewriteRuleTokenStream(adaptor,"token STRING_LITERAL");
        RewriteRuleTokenStream stream_BANG=new RewriteRuleTokenStream(adaptor,"token BANG");
        RewriteRuleTokenStream stream_92=new RewriteRuleTokenStream(adaptor,"token 92");
        RewriteRuleTokenStream stream_CHAR_LITERAL=new RewriteRuleTokenStream(adaptor,"token CHAR_LITERAL");
        RewriteRuleTokenStream stream_ROOT=new RewriteRuleTokenStream(adaptor,"token ROOT");
        RewriteRuleTokenStream stream_TOKEN_REF=new RewriteRuleTokenStream(adaptor,"token TOKEN_REF");
        RewriteRuleTokenStream stream_ARG_ACTION=new RewriteRuleTokenStream(adaptor,"token ARG_ACTION");

        try {
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:305:5: ( ( CHAR_LITERAL -> CHAR_LITERAL | TOKEN_REF ( ARG_ACTION -> ^( TOKEN_REF ARG_ACTION ) | -> TOKEN_REF ) | STRING_LITERAL -> STRING_LITERAL | '.' -> '.' ) ( '^' -> ^( '^' $terminal) | '!' -> ^( '!' $terminal) )? )
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:305:9: ( CHAR_LITERAL -> CHAR_LITERAL | TOKEN_REF ( ARG_ACTION -> ^( TOKEN_REF ARG_ACTION ) | -> TOKEN_REF ) | STRING_LITERAL -> STRING_LITERAL | '.' -> '.' ) ( '^' -> ^( '^' $terminal) | '!' -> ^( '!' $terminal) )?
            {
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:305:9: ( CHAR_LITERAL -> CHAR_LITERAL | TOKEN_REF ( ARG_ACTION -> ^( TOKEN_REF ARG_ACTION ) | -> TOKEN_REF ) | STRING_LITERAL -> STRING_LITERAL | '.' -> '.' )
            int alt59=4;
            switch ( input.LA(1) ) {
            case CHAR_LITERAL:
                {
                alt59=1;
                }
                break;
            case TOKEN_REF:
                {
                alt59=2;
                }
                break;
            case STRING_LITERAL:
                {
                alt59=3;
                }
                break;
            case 92:
                {
                alt59=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 59, 0, input);

                throw nvae;
            }

            switch (alt59) {
                case 1 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:305:11: CHAR_LITERAL
                    {
                    CHAR_LITERAL121=(Token)match(input,CHAR_LITERAL,FOLLOW_CHAR_LITERAL_in_terminal2252); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_CHAR_LITERAL.add(CHAR_LITERAL121);



                    // AST REWRITE
                    // elements: CHAR_LITERAL
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 305:27: -> CHAR_LITERAL
                    {
                        adaptor.addChild(root_0, stream_CHAR_LITERAL.nextNode());

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:307:7: TOKEN_REF ( ARG_ACTION -> ^( TOKEN_REF ARG_ACTION ) | -> TOKEN_REF )
                    {
                    TOKEN_REF122=(Token)match(input,TOKEN_REF,FOLLOW_TOKEN_REF_in_terminal2274); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_TOKEN_REF.add(TOKEN_REF122);

                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:308:4: ( ARG_ACTION -> ^( TOKEN_REF ARG_ACTION ) | -> TOKEN_REF )
                    int alt58=2;
                    int LA58_0 = input.LA(1);

                    if ( (LA58_0==ARG_ACTION) ) {
                        alt58=1;
                    }
                    else if ( (LA58_0==SEMPRED||(LA58_0>=TREE_BEGIN && LA58_0<=REWRITE)||(LA58_0>=TOKEN_REF && LA58_0<=ACTION)||LA58_0==RULE_REF||LA58_0==69||LA58_0==74||(LA58_0>=82 && LA58_0<=84)||(LA58_0>=89 && LA58_0<=92)) ) {
                        alt58=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 58, 0, input);

                        throw nvae;
                    }
                    switch (alt58) {
                        case 1 :
                            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:308:6: ARG_ACTION
                            {
                            ARG_ACTION123=(Token)match(input,ARG_ACTION,FOLLOW_ARG_ACTION_in_terminal2281); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_ARG_ACTION.add(ARG_ACTION123);



                            // AST REWRITE
                            // elements: TOKEN_REF, ARG_ACTION
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 308:20: -> ^( TOKEN_REF ARG_ACTION )
                            {
                                // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:308:23: ^( TOKEN_REF ARG_ACTION )
                                {
                                CommonTree root_1 = (CommonTree)adaptor.nil();
                                root_1 = (CommonTree)adaptor.becomeRoot(stream_TOKEN_REF.nextNode(), root_1);

                                adaptor.addChild(root_1, stream_ARG_ACTION.nextNode());

                                adaptor.addChild(root_0, root_1);
                                }

                            }

                            retval.tree = root_0;}
                            }
                            break;
                        case 2 :
                            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:309:12: 
                            {

                            // AST REWRITE
                            // elements: TOKEN_REF
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 309:12: -> TOKEN_REF
                            {
                                adaptor.addChild(root_0, stream_TOKEN_REF.nextNode());

                            }

                            retval.tree = root_0;}
                            }
                            break;

                    }


                    }
                    break;
                case 3 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:311:7: STRING_LITERAL
                    {
                    STRING_LITERAL124=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_terminal2320); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_STRING_LITERAL.add(STRING_LITERAL124);



                    // AST REWRITE
                    // elements: STRING_LITERAL
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 311:25: -> STRING_LITERAL
                    {
                        adaptor.addChild(root_0, stream_STRING_LITERAL.nextNode());

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 4 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:312:7: '.'
                    {
                    char_literal125=(Token)match(input,92,FOLLOW_92_in_terminal2335); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_92.add(char_literal125);



                    // AST REWRITE
                    // elements: 92
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 312:17: -> '.'
                    {
                        adaptor.addChild(root_0, stream_92.nextNode());

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }

            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:314:3: ( '^' -> ^( '^' $terminal) | '!' -> ^( '!' $terminal) )?
            int alt60=3;
            int LA60_0 = input.LA(1);

            if ( (LA60_0==ROOT) ) {
                alt60=1;
            }
            else if ( (LA60_0==BANG) ) {
                alt60=2;
            }
            switch (alt60) {
                case 1 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:314:5: '^'
                    {
                    char_literal126=(Token)match(input,ROOT,FOLLOW_ROOT_in_terminal2356); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ROOT.add(char_literal126);



                    // AST REWRITE
                    // elements: terminal, ROOT
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 314:15: -> ^( '^' $terminal)
                    {
                        // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:314:18: ^( '^' $terminal)
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_ROOT.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_retval.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:315:5: '!'
                    {
                    char_literal127=(Token)match(input,BANG,FOLLOW_BANG_in_terminal2377); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_BANG.add(char_literal127);



                    // AST REWRITE
                    // elements: terminal, BANG
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 315:15: -> ^( '!' $terminal)
                    {
                        // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:315:18: ^( '!' $terminal)
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_BANG.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_retval.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "terminal"

    public static class notTerminal_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "notTerminal"
    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:319:1: notTerminal : ( CHAR_LITERAL | TOKEN_REF | STRING_LITERAL );
    public final ANTLRv3Parser.notTerminal_return notTerminal() throws RecognitionException {
        ANTLRv3Parser.notTerminal_return retval = new ANTLRv3Parser.notTerminal_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token set128=null;

        CommonTree set128_tree=null;

        try {
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:320:2: ( CHAR_LITERAL | TOKEN_REF | STRING_LITERAL )
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:
            {
            root_0 = (CommonTree)adaptor.nil();

            set128=(Token)input.LT(1);
            if ( (input.LA(1)>=TOKEN_REF && input.LA(1)<=CHAR_LITERAL) ) {
                input.consume();
                if ( state.backtracking==0 ) adaptor.addChild(root_0, (CommonTree)adaptor.create(set128));
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "notTerminal"

    public static class ebnfSuffix_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "ebnfSuffix"
    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:325:1: ebnfSuffix : ( '?' -> OPTIONAL[op] | '*' -> CLOSURE[op] | '+' -> POSITIVE_CLOSURE[op] );
    public final ANTLRv3Parser.ebnfSuffix_return ebnfSuffix() throws RecognitionException {
        ANTLRv3Parser.ebnfSuffix_return retval = new ANTLRv3Parser.ebnfSuffix_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal129=null;
        Token char_literal130=null;
        Token char_literal131=null;

        CommonTree char_literal129_tree=null;
        CommonTree char_literal130_tree=null;
        CommonTree char_literal131_tree=null;
        RewriteRuleTokenStream stream_91=new RewriteRuleTokenStream(adaptor,"token 91");
        RewriteRuleTokenStream stream_90=new RewriteRuleTokenStream(adaptor,"token 90");
        RewriteRuleTokenStream stream_74=new RewriteRuleTokenStream(adaptor,"token 74");


        	Token op = input.LT(1);

        try {
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:329:2: ( '?' -> OPTIONAL[op] | '*' -> CLOSURE[op] | '+' -> POSITIVE_CLOSURE[op] )
            int alt61=3;
            switch ( input.LA(1) ) {
            case 90:
                {
                alt61=1;
                }
                break;
            case 74:
                {
                alt61=2;
                }
                break;
            case 91:
                {
                alt61=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 61, 0, input);

                throw nvae;
            }

            switch (alt61) {
                case 1 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:329:4: '?'
                    {
                    char_literal129=(Token)match(input,90,FOLLOW_90_in_ebnfSuffix2437); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_90.add(char_literal129);



                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 329:8: -> OPTIONAL[op]
                    {
                        adaptor.addChild(root_0, (CommonTree)adaptor.create(OPTIONAL, op));

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:330:6: '*'
                    {
                    char_literal130=(Token)match(input,74,FOLLOW_74_in_ebnfSuffix2449); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_74.add(char_literal130);



                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 330:10: -> CLOSURE[op]
                    {
                        adaptor.addChild(root_0, (CommonTree)adaptor.create(CLOSURE, op));

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 3 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:331:7: '+'
                    {
                    char_literal131=(Token)match(input,91,FOLLOW_91_in_ebnfSuffix2462); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_91.add(char_literal131);



                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 331:11: -> POSITIVE_CLOSURE[op]
                    {
                        adaptor.addChild(root_0, (CommonTree)adaptor.create(POSITIVE_CLOSURE, op));

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "ebnfSuffix"

    public static class rewrite_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "rewrite"
    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:338:1: rewrite : ( (rew+= '->' preds+= SEMPRED predicated+= rewrite_alternative )* rew2= '->' last= rewrite_alternative -> ( ^( $rew $preds $predicated) )* ^( $rew2 $last) | );
    public final ANTLRv3Parser.rewrite_return rewrite() throws RecognitionException {
        ANTLRv3Parser.rewrite_return retval = new ANTLRv3Parser.rewrite_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token rew2=null;
        Token rew=null;
        Token preds=null;
        List list_rew=null;
        List list_preds=null;
        List list_predicated=null;
        ANTLRv3Parser.rewrite_alternative_return last = null;

        RuleReturnScope predicated = null;
        CommonTree rew2_tree=null;
        CommonTree rew_tree=null;
        CommonTree preds_tree=null;
        RewriteRuleTokenStream stream_SEMPRED=new RewriteRuleTokenStream(adaptor,"token SEMPRED");
        RewriteRuleTokenStream stream_REWRITE=new RewriteRuleTokenStream(adaptor,"token REWRITE");
        RewriteRuleSubtreeStream stream_rewrite_alternative=new RewriteRuleSubtreeStream(adaptor,"rule rewrite_alternative");

        	Token firstToken = input.LT(1);

        try {
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:342:2: ( (rew+= '->' preds+= SEMPRED predicated+= rewrite_alternative )* rew2= '->' last= rewrite_alternative -> ( ^( $rew $preds $predicated) )* ^( $rew2 $last) | )
            int alt63=2;
            int LA63_0 = input.LA(1);

            if ( (LA63_0==REWRITE) ) {
                alt63=1;
            }
            else if ( (LA63_0==69||(LA63_0>=83 && LA63_0<=84)) ) {
                alt63=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 63, 0, input);

                throw nvae;
            }
            switch (alt63) {
                case 1 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:342:4: (rew+= '->' preds+= SEMPRED predicated+= rewrite_alternative )* rew2= '->' last= rewrite_alternative
                    {
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:342:4: (rew+= '->' preds+= SEMPRED predicated+= rewrite_alternative )*
                    loop62:
                    do {
                        int alt62=2;
                        int LA62_0 = input.LA(1);

                        if ( (LA62_0==REWRITE) ) {
                            int LA62_1 = input.LA(2);

                            if ( (LA62_1==SEMPRED) ) {
                                alt62=1;
                            }


                        }


                        switch (alt62) {
                    	case 1 :
                    	    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:342:5: rew+= '->' preds+= SEMPRED predicated+= rewrite_alternative
                    	    {
                    	    rew=(Token)match(input,REWRITE,FOLLOW_REWRITE_in_rewrite2491); if (state.failed) return retval; 
                    	    if ( state.backtracking==0 ) stream_REWRITE.add(rew);

                    	    if (list_rew==null) list_rew=new ArrayList();
                    	    list_rew.add(rew);

                    	    preds=(Token)match(input,SEMPRED,FOLLOW_SEMPRED_in_rewrite2495); if (state.failed) return retval; 
                    	    if ( state.backtracking==0 ) stream_SEMPRED.add(preds);

                    	    if (list_preds==null) list_preds=new ArrayList();
                    	    list_preds.add(preds);

                    	    pushFollow(FOLLOW_rewrite_alternative_in_rewrite2499);
                    	    predicated=rewrite_alternative();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_rewrite_alternative.add(predicated.getTree());
                    	    if (list_predicated==null) list_predicated=new ArrayList();
                    	    list_predicated.add(predicated.getTree());


                    	    }
                    	    break;

                    	default :
                    	    break loop62;
                        }
                    } while (true);

                    rew2=(Token)match(input,REWRITE,FOLLOW_REWRITE_in_rewrite2507); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_REWRITE.add(rew2);

                    pushFollow(FOLLOW_rewrite_alternative_in_rewrite2511);
                    last=rewrite_alternative();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_rewrite_alternative.add(last.getTree());


                    // AST REWRITE
                    // elements: rew, last, preds, rew2, predicated
                    // token labels: rew2
                    // rule labels: retval, last
                    // token list labels: rew, preds
                    // rule list labels: predicated
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleTokenStream stream_rew2=new RewriteRuleTokenStream(adaptor,"token rew2",rew2);
                    RewriteRuleTokenStream stream_rew=new RewriteRuleTokenStream(adaptor,"token rew", list_rew);
                    RewriteRuleTokenStream stream_preds=new RewriteRuleTokenStream(adaptor,"token preds", list_preds);
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_last=new RewriteRuleSubtreeStream(adaptor,"rule last",last!=null?last.tree:null);
                    RewriteRuleSubtreeStream stream_predicated=new RewriteRuleSubtreeStream(adaptor,"token predicated",list_predicated);
                    root_0 = (CommonTree)adaptor.nil();
                    // 344:9: -> ( ^( $rew $preds $predicated) )* ^( $rew2 $last)
                    {
                        // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:344:12: ( ^( $rew $preds $predicated) )*
                        while ( stream_rew.hasNext()||stream_preds.hasNext()||stream_predicated.hasNext() ) {
                            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:344:12: ^( $rew $preds $predicated)
                            {
                            CommonTree root_1 = (CommonTree)adaptor.nil();
                            root_1 = (CommonTree)adaptor.becomeRoot(stream_rew.nextNode(), root_1);

                            adaptor.addChild(root_1, stream_preds.nextNode());
                            adaptor.addChild(root_1, stream_predicated.nextTree());

                            adaptor.addChild(root_0, root_1);
                            }

                        }
                        stream_rew.reset();
                        stream_preds.reset();
                        stream_predicated.reset();
                        // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:344:40: ^( $rew2 $last)
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_rew2.nextNode(), root_1);

                        adaptor.addChild(root_1, stream_last.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:346:2: 
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "rewrite"

    public static class rewrite_alternative_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "rewrite_alternative"
    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:348:1: rewrite_alternative options {backtrack=true; } : ( rewrite_template | rewrite_tree_alternative | -> ^( ALT[\"ALT\"] EPSILON[\"EPSILON\"] EOA[\"EOA\"] ) );
    public final ANTLRv3Parser.rewrite_alternative_return rewrite_alternative() throws RecognitionException {
        ANTLRv3Parser.rewrite_alternative_return retval = new ANTLRv3Parser.rewrite_alternative_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        ANTLRv3Parser.rewrite_template_return rewrite_template132 = null;

        ANTLRv3Parser.rewrite_tree_alternative_return rewrite_tree_alternative133 = null;



        try {
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:350:2: ( rewrite_template | rewrite_tree_alternative | -> ^( ALT[\"ALT\"] EPSILON[\"EPSILON\"] EOA[\"EOA\"] ) )
            int alt64=3;
            alt64 = dfa64.predict(input);
            switch (alt64) {
                case 1 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:350:4: rewrite_template
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_rewrite_template_in_rewrite_alternative2562);
                    rewrite_template132=rewrite_template();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, rewrite_template132.getTree());

                    }
                    break;
                case 2 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:351:4: rewrite_tree_alternative
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_rewrite_tree_alternative_in_rewrite_alternative2567);
                    rewrite_tree_alternative133=rewrite_tree_alternative();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, rewrite_tree_alternative133.getTree());

                    }
                    break;
                case 3 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:352:29: 
                    {

                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 352:29: -> ^( ALT[\"ALT\"] EPSILON[\"EPSILON\"] EOA[\"EOA\"] )
                    {
                        // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:352:32: ^( ALT[\"ALT\"] EPSILON[\"EPSILON\"] EOA[\"EOA\"] )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ALT, "ALT"), root_1);

                        adaptor.addChild(root_1, (CommonTree)adaptor.create(EPSILON, "EPSILON"));
                        adaptor.addChild(root_1, (CommonTree)adaptor.create(EOA, "EOA"));

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "rewrite_alternative"

    public static class rewrite_tree_block_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "rewrite_tree_block"
    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:355:1: rewrite_tree_block : lp= '(' rewrite_tree_alternative ')' -> ^( BLOCK[$lp,\"BLOCK\"] rewrite_tree_alternative EOB[$lp,\"EOB\"] ) ;
    public final ANTLRv3Parser.rewrite_tree_block_return rewrite_tree_block() throws RecognitionException {
        ANTLRv3Parser.rewrite_tree_block_return retval = new ANTLRv3Parser.rewrite_tree_block_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token lp=null;
        Token char_literal135=null;
        ANTLRv3Parser.rewrite_tree_alternative_return rewrite_tree_alternative134 = null;


        CommonTree lp_tree=null;
        CommonTree char_literal135_tree=null;
        RewriteRuleTokenStream stream_82=new RewriteRuleTokenStream(adaptor,"token 82");
        RewriteRuleTokenStream stream_84=new RewriteRuleTokenStream(adaptor,"token 84");
        RewriteRuleSubtreeStream stream_rewrite_tree_alternative=new RewriteRuleSubtreeStream(adaptor,"rule rewrite_tree_alternative");
        try {
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:356:5: (lp= '(' rewrite_tree_alternative ')' -> ^( BLOCK[$lp,\"BLOCK\"] rewrite_tree_alternative EOB[$lp,\"EOB\"] ) )
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:356:9: lp= '(' rewrite_tree_alternative ')'
            {
            lp=(Token)match(input,82,FOLLOW_82_in_rewrite_tree_block2609); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_82.add(lp);

            pushFollow(FOLLOW_rewrite_tree_alternative_in_rewrite_tree_block2611);
            rewrite_tree_alternative134=rewrite_tree_alternative();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_rewrite_tree_alternative.add(rewrite_tree_alternative134.getTree());
            char_literal135=(Token)match(input,84,FOLLOW_84_in_rewrite_tree_block2613); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_84.add(char_literal135);



            // AST REWRITE
            // elements: rewrite_tree_alternative
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 357:6: -> ^( BLOCK[$lp,\"BLOCK\"] rewrite_tree_alternative EOB[$lp,\"EOB\"] )
            {
                // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:357:9: ^( BLOCK[$lp,\"BLOCK\"] rewrite_tree_alternative EOB[$lp,\"EOB\"] )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(BLOCK, lp, "BLOCK"), root_1);

                adaptor.addChild(root_1, stream_rewrite_tree_alternative.nextTree());
                adaptor.addChild(root_1, (CommonTree)adaptor.create(EOB, lp, "EOB"));

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "rewrite_tree_block"

    public static class rewrite_tree_alternative_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "rewrite_tree_alternative"
    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:360:1: rewrite_tree_alternative : ( rewrite_tree_element )+ -> ^( ALT[\"ALT\"] ( rewrite_tree_element )+ EOA[\"EOA\"] ) ;
    public final ANTLRv3Parser.rewrite_tree_alternative_return rewrite_tree_alternative() throws RecognitionException {
        ANTLRv3Parser.rewrite_tree_alternative_return retval = new ANTLRv3Parser.rewrite_tree_alternative_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        ANTLRv3Parser.rewrite_tree_element_return rewrite_tree_element136 = null;


        RewriteRuleSubtreeStream stream_rewrite_tree_element=new RewriteRuleSubtreeStream(adaptor,"rule rewrite_tree_element");
        try {
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:361:5: ( ( rewrite_tree_element )+ -> ^( ALT[\"ALT\"] ( rewrite_tree_element )+ EOA[\"EOA\"] ) )
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:361:7: ( rewrite_tree_element )+
            {
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:361:7: ( rewrite_tree_element )+
            int cnt65=0;
            loop65:
            do {
                int alt65=2;
                int LA65_0 = input.LA(1);

                if ( (LA65_0==TREE_BEGIN||(LA65_0>=TOKEN_REF && LA65_0<=ACTION)||LA65_0==RULE_REF||LA65_0==82||LA65_0==93) ) {
                    alt65=1;
                }


                switch (alt65) {
            	case 1 :
            	    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:361:7: rewrite_tree_element
            	    {
            	    pushFollow(FOLLOW_rewrite_tree_element_in_rewrite_tree_alternative2647);
            	    rewrite_tree_element136=rewrite_tree_element();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_rewrite_tree_element.add(rewrite_tree_element136.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt65 >= 1 ) break loop65;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(65, input);
                        throw eee;
                }
                cnt65++;
            } while (true);



            // AST REWRITE
            // elements: rewrite_tree_element
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 361:29: -> ^( ALT[\"ALT\"] ( rewrite_tree_element )+ EOA[\"EOA\"] )
            {
                // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:361:32: ^( ALT[\"ALT\"] ( rewrite_tree_element )+ EOA[\"EOA\"] )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ALT, "ALT"), root_1);

                if ( !(stream_rewrite_tree_element.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_rewrite_tree_element.hasNext() ) {
                    adaptor.addChild(root_1, stream_rewrite_tree_element.nextTree());

                }
                stream_rewrite_tree_element.reset();
                adaptor.addChild(root_1, (CommonTree)adaptor.create(EOA, "EOA"));

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "rewrite_tree_alternative"

    public static class rewrite_tree_element_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "rewrite_tree_element"
    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:364:1: rewrite_tree_element : ( rewrite_tree_atom | rewrite_tree_atom ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] rewrite_tree_atom EOA[\"EOA\"] ) EOB[\"EOB\"] ) ) | rewrite_tree ( ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] rewrite_tree EOA[\"EOA\"] ) EOB[\"EOB\"] ) ) | -> rewrite_tree ) | rewrite_tree_ebnf );
    public final ANTLRv3Parser.rewrite_tree_element_return rewrite_tree_element() throws RecognitionException {
        ANTLRv3Parser.rewrite_tree_element_return retval = new ANTLRv3Parser.rewrite_tree_element_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        ANTLRv3Parser.rewrite_tree_atom_return rewrite_tree_atom137 = null;

        ANTLRv3Parser.rewrite_tree_atom_return rewrite_tree_atom138 = null;

        ANTLRv3Parser.ebnfSuffix_return ebnfSuffix139 = null;

        ANTLRv3Parser.rewrite_tree_return rewrite_tree140 = null;

        ANTLRv3Parser.ebnfSuffix_return ebnfSuffix141 = null;

        ANTLRv3Parser.rewrite_tree_ebnf_return rewrite_tree_ebnf142 = null;


        RewriteRuleSubtreeStream stream_rewrite_tree=new RewriteRuleSubtreeStream(adaptor,"rule rewrite_tree");
        RewriteRuleSubtreeStream stream_ebnfSuffix=new RewriteRuleSubtreeStream(adaptor,"rule ebnfSuffix");
        RewriteRuleSubtreeStream stream_rewrite_tree_atom=new RewriteRuleSubtreeStream(adaptor,"rule rewrite_tree_atom");
        try {
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:365:2: ( rewrite_tree_atom | rewrite_tree_atom ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] rewrite_tree_atom EOA[\"EOA\"] ) EOB[\"EOB\"] ) ) | rewrite_tree ( ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] rewrite_tree EOA[\"EOA\"] ) EOB[\"EOB\"] ) ) | -> rewrite_tree ) | rewrite_tree_ebnf )
            int alt67=4;
            alt67 = dfa67.predict(input);
            switch (alt67) {
                case 1 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:365:4: rewrite_tree_atom
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_rewrite_tree_atom_in_rewrite_tree_element2675);
                    rewrite_tree_atom137=rewrite_tree_atom();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, rewrite_tree_atom137.getTree());

                    }
                    break;
                case 2 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:366:4: rewrite_tree_atom ebnfSuffix
                    {
                    pushFollow(FOLLOW_rewrite_tree_atom_in_rewrite_tree_element2680);
                    rewrite_tree_atom138=rewrite_tree_atom();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_rewrite_tree_atom.add(rewrite_tree_atom138.getTree());
                    pushFollow(FOLLOW_ebnfSuffix_in_rewrite_tree_element2682);
                    ebnfSuffix139=ebnfSuffix();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_ebnfSuffix.add(ebnfSuffix139.getTree());


                    // AST REWRITE
                    // elements: ebnfSuffix, rewrite_tree_atom
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 367:3: -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] rewrite_tree_atom EOA[\"EOA\"] ) EOB[\"EOB\"] ) )
                    {
                        // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:367:6: ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] rewrite_tree_atom EOA[\"EOA\"] ) EOB[\"EOB\"] ) )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_ebnfSuffix.nextNode(), root_1);

                        // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:367:20: ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] rewrite_tree_atom EOA[\"EOA\"] ) EOB[\"EOB\"] )
                        {
                        CommonTree root_2 = (CommonTree)adaptor.nil();
                        root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(BLOCK, "BLOCK"), root_2);

                        // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:367:37: ^( ALT[\"ALT\"] rewrite_tree_atom EOA[\"EOA\"] )
                        {
                        CommonTree root_3 = (CommonTree)adaptor.nil();
                        root_3 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ALT, "ALT"), root_3);

                        adaptor.addChild(root_3, stream_rewrite_tree_atom.nextTree());
                        adaptor.addChild(root_3, (CommonTree)adaptor.create(EOA, "EOA"));

                        adaptor.addChild(root_2, root_3);
                        }
                        adaptor.addChild(root_2, (CommonTree)adaptor.create(EOB, "EOB"));

                        adaptor.addChild(root_1, root_2);
                        }

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 3 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:368:6: rewrite_tree ( ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] rewrite_tree EOA[\"EOA\"] ) EOB[\"EOB\"] ) ) | -> rewrite_tree )
                    {
                    pushFollow(FOLLOW_rewrite_tree_in_rewrite_tree_element2716);
                    rewrite_tree140=rewrite_tree();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_rewrite_tree.add(rewrite_tree140.getTree());
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:369:3: ( ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] rewrite_tree EOA[\"EOA\"] ) EOB[\"EOB\"] ) ) | -> rewrite_tree )
                    int alt66=2;
                    int LA66_0 = input.LA(1);

                    if ( (LA66_0==74||(LA66_0>=90 && LA66_0<=91)) ) {
                        alt66=1;
                    }
                    else if ( (LA66_0==EOF||LA66_0==TREE_BEGIN||LA66_0==REWRITE||(LA66_0>=TOKEN_REF && LA66_0<=ACTION)||LA66_0==RULE_REF||LA66_0==69||(LA66_0>=82 && LA66_0<=84)||LA66_0==93) ) {
                        alt66=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 66, 0, input);

                        throw nvae;
                    }
                    switch (alt66) {
                        case 1 :
                            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:369:5: ebnfSuffix
                            {
                            pushFollow(FOLLOW_ebnfSuffix_in_rewrite_tree_element2722);
                            ebnfSuffix141=ebnfSuffix();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_ebnfSuffix.add(ebnfSuffix141.getTree());


                            // AST REWRITE
                            // elements: rewrite_tree, ebnfSuffix
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 370:4: -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] rewrite_tree EOA[\"EOA\"] ) EOB[\"EOB\"] ) )
                            {
                                // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:370:7: ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] rewrite_tree EOA[\"EOA\"] ) EOB[\"EOB\"] ) )
                                {
                                CommonTree root_1 = (CommonTree)adaptor.nil();
                                root_1 = (CommonTree)adaptor.becomeRoot(stream_ebnfSuffix.nextNode(), root_1);

                                // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:370:20: ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] rewrite_tree EOA[\"EOA\"] ) EOB[\"EOB\"] )
                                {
                                CommonTree root_2 = (CommonTree)adaptor.nil();
                                root_2 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(BLOCK, "BLOCK"), root_2);

                                // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:370:37: ^( ALT[\"ALT\"] rewrite_tree EOA[\"EOA\"] )
                                {
                                CommonTree root_3 = (CommonTree)adaptor.nil();
                                root_3 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ALT, "ALT"), root_3);

                                adaptor.addChild(root_3, stream_rewrite_tree.nextTree());
                                adaptor.addChild(root_3, (CommonTree)adaptor.create(EOA, "EOA"));

                                adaptor.addChild(root_2, root_3);
                                }
                                adaptor.addChild(root_2, (CommonTree)adaptor.create(EOB, "EOB"));

                                adaptor.addChild(root_1, root_2);
                                }

                                adaptor.addChild(root_0, root_1);
                                }

                            }

                            retval.tree = root_0;}
                            }
                            break;
                        case 2 :
                            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:371:5: 
                            {

                            // AST REWRITE
                            // elements: rewrite_tree
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 371:5: -> rewrite_tree
                            {
                                adaptor.addChild(root_0, stream_rewrite_tree.nextTree());

                            }

                            retval.tree = root_0;}
                            }
                            break;

                    }


                    }
                    break;
                case 4 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:373:6: rewrite_tree_ebnf
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_rewrite_tree_ebnf_in_rewrite_tree_element2768);
                    rewrite_tree_ebnf142=rewrite_tree_ebnf();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, rewrite_tree_ebnf142.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "rewrite_tree_element"

    public static class rewrite_tree_atom_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "rewrite_tree_atom"
    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:376:1: rewrite_tree_atom : ( CHAR_LITERAL | TOKEN_REF ( ARG_ACTION )? -> ^( TOKEN_REF ( ARG_ACTION )? ) | RULE_REF | STRING_LITERAL | d= '$' id -> LABEL[$d,$id.text] | ACTION );
    public final ANTLRv3Parser.rewrite_tree_atom_return rewrite_tree_atom() throws RecognitionException {
        ANTLRv3Parser.rewrite_tree_atom_return retval = new ANTLRv3Parser.rewrite_tree_atom_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token d=null;
        Token CHAR_LITERAL143=null;
        Token TOKEN_REF144=null;
        Token ARG_ACTION145=null;
        Token RULE_REF146=null;
        Token STRING_LITERAL147=null;
        Token ACTION149=null;
        ANTLRv3Parser.id_return id148 = null;


        CommonTree d_tree=null;
        CommonTree CHAR_LITERAL143_tree=null;
        CommonTree TOKEN_REF144_tree=null;
        CommonTree ARG_ACTION145_tree=null;
        CommonTree RULE_REF146_tree=null;
        CommonTree STRING_LITERAL147_tree=null;
        CommonTree ACTION149_tree=null;
        RewriteRuleTokenStream stream_93=new RewriteRuleTokenStream(adaptor,"token 93");
        RewriteRuleTokenStream stream_TOKEN_REF=new RewriteRuleTokenStream(adaptor,"token TOKEN_REF");
        RewriteRuleTokenStream stream_ARG_ACTION=new RewriteRuleTokenStream(adaptor,"token ARG_ACTION");
        RewriteRuleSubtreeStream stream_id=new RewriteRuleSubtreeStream(adaptor,"rule id");
        try {
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:377:5: ( CHAR_LITERAL | TOKEN_REF ( ARG_ACTION )? -> ^( TOKEN_REF ( ARG_ACTION )? ) | RULE_REF | STRING_LITERAL | d= '$' id -> LABEL[$d,$id.text] | ACTION )
            int alt69=6;
            switch ( input.LA(1) ) {
            case CHAR_LITERAL:
                {
                alt69=1;
                }
                break;
            case TOKEN_REF:
                {
                alt69=2;
                }
                break;
            case RULE_REF:
                {
                alt69=3;
                }
                break;
            case STRING_LITERAL:
                {
                alt69=4;
                }
                break;
            case 93:
                {
                alt69=5;
                }
                break;
            case ACTION:
                {
                alt69=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 69, 0, input);

                throw nvae;
            }

            switch (alt69) {
                case 1 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:377:9: CHAR_LITERAL
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    CHAR_LITERAL143=(Token)match(input,CHAR_LITERAL,FOLLOW_CHAR_LITERAL_in_rewrite_tree_atom2784); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    CHAR_LITERAL143_tree = (CommonTree)adaptor.create(CHAR_LITERAL143);
                    adaptor.addChild(root_0, CHAR_LITERAL143_tree);
                    }

                    }
                    break;
                case 2 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:378:6: TOKEN_REF ( ARG_ACTION )?
                    {
                    TOKEN_REF144=(Token)match(input,TOKEN_REF,FOLLOW_TOKEN_REF_in_rewrite_tree_atom2791); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_TOKEN_REF.add(TOKEN_REF144);

                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:378:16: ( ARG_ACTION )?
                    int alt68=2;
                    int LA68_0 = input.LA(1);

                    if ( (LA68_0==ARG_ACTION) ) {
                        alt68=1;
                    }
                    switch (alt68) {
                        case 1 :
                            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:378:16: ARG_ACTION
                            {
                            ARG_ACTION145=(Token)match(input,ARG_ACTION,FOLLOW_ARG_ACTION_in_rewrite_tree_atom2793); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_ARG_ACTION.add(ARG_ACTION145);


                            }
                            break;

                    }



                    // AST REWRITE
                    // elements: TOKEN_REF, ARG_ACTION
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 378:28: -> ^( TOKEN_REF ( ARG_ACTION )? )
                    {
                        // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:378:31: ^( TOKEN_REF ( ARG_ACTION )? )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_TOKEN_REF.nextNode(), root_1);

                        // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:378:43: ( ARG_ACTION )?
                        if ( stream_ARG_ACTION.hasNext() ) {
                            adaptor.addChild(root_1, stream_ARG_ACTION.nextNode());

                        }
                        stream_ARG_ACTION.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 3 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:379:9: RULE_REF
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    RULE_REF146=(Token)match(input,RULE_REF,FOLLOW_RULE_REF_in_rewrite_tree_atom2814); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    RULE_REF146_tree = (CommonTree)adaptor.create(RULE_REF146);
                    adaptor.addChild(root_0, RULE_REF146_tree);
                    }

                    }
                    break;
                case 4 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:380:6: STRING_LITERAL
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    STRING_LITERAL147=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_rewrite_tree_atom2821); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    STRING_LITERAL147_tree = (CommonTree)adaptor.create(STRING_LITERAL147);
                    adaptor.addChild(root_0, STRING_LITERAL147_tree);
                    }

                    }
                    break;
                case 5 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:381:6: d= '$' id
                    {
                    d=(Token)match(input,93,FOLLOW_93_in_rewrite_tree_atom2830); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_93.add(d);

                    pushFollow(FOLLOW_id_in_rewrite_tree_atom2832);
                    id148=id();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_id.add(id148.getTree());


                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 381:15: -> LABEL[$d,$id.text]
                    {
                        adaptor.addChild(root_0, (CommonTree)adaptor.create(LABEL, d, (id148!=null?input.toString(id148.start,id148.stop):null)));

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 6 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:382:4: ACTION
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    ACTION149=(Token)match(input,ACTION,FOLLOW_ACTION_in_rewrite_tree_atom2843); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ACTION149_tree = (CommonTree)adaptor.create(ACTION149);
                    adaptor.addChild(root_0, ACTION149_tree);
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "rewrite_tree_atom"

    public static class rewrite_tree_ebnf_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "rewrite_tree_ebnf"
    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:385:1: rewrite_tree_ebnf : rewrite_tree_block ebnfSuffix -> ^( ebnfSuffix rewrite_tree_block ) ;
    public final ANTLRv3Parser.rewrite_tree_ebnf_return rewrite_tree_ebnf() throws RecognitionException {
        ANTLRv3Parser.rewrite_tree_ebnf_return retval = new ANTLRv3Parser.rewrite_tree_ebnf_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        ANTLRv3Parser.rewrite_tree_block_return rewrite_tree_block150 = null;

        ANTLRv3Parser.ebnfSuffix_return ebnfSuffix151 = null;


        RewriteRuleSubtreeStream stream_ebnfSuffix=new RewriteRuleSubtreeStream(adaptor,"rule ebnfSuffix");
        RewriteRuleSubtreeStream stream_rewrite_tree_block=new RewriteRuleSubtreeStream(adaptor,"rule rewrite_tree_block");

            Token firstToken = input.LT(1);

        try {
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:393:2: ( rewrite_tree_block ebnfSuffix -> ^( ebnfSuffix rewrite_tree_block ) )
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:393:4: rewrite_tree_block ebnfSuffix
            {
            pushFollow(FOLLOW_rewrite_tree_block_in_rewrite_tree_ebnf2864);
            rewrite_tree_block150=rewrite_tree_block();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_rewrite_tree_block.add(rewrite_tree_block150.getTree());
            pushFollow(FOLLOW_ebnfSuffix_in_rewrite_tree_ebnf2866);
            ebnfSuffix151=ebnfSuffix();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_ebnfSuffix.add(ebnfSuffix151.getTree());


            // AST REWRITE
            // elements: ebnfSuffix, rewrite_tree_block
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 393:34: -> ^( ebnfSuffix rewrite_tree_block )
            {
                // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:393:37: ^( ebnfSuffix rewrite_tree_block )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_ebnfSuffix.nextNode(), root_1);

                adaptor.addChild(root_1, stream_rewrite_tree_block.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {

              	((CommonTree)retval.tree).getToken().setLine(firstToken.getLine());
              	((CommonTree)retval.tree).getToken().setCharPositionInLine(firstToken.getCharPositionInLine());

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "rewrite_tree_ebnf"

    public static class rewrite_tree_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "rewrite_tree"
    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:396:1: rewrite_tree : '^(' rewrite_tree_atom ( rewrite_tree_element )* ')' -> ^( TREE_BEGIN rewrite_tree_atom ( rewrite_tree_element )* ) ;
    public final ANTLRv3Parser.rewrite_tree_return rewrite_tree() throws RecognitionException {
        ANTLRv3Parser.rewrite_tree_return retval = new ANTLRv3Parser.rewrite_tree_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal152=null;
        Token char_literal155=null;
        ANTLRv3Parser.rewrite_tree_atom_return rewrite_tree_atom153 = null;

        ANTLRv3Parser.rewrite_tree_element_return rewrite_tree_element154 = null;


        CommonTree string_literal152_tree=null;
        CommonTree char_literal155_tree=null;
        RewriteRuleTokenStream stream_TREE_BEGIN=new RewriteRuleTokenStream(adaptor,"token TREE_BEGIN");
        RewriteRuleTokenStream stream_84=new RewriteRuleTokenStream(adaptor,"token 84");
        RewriteRuleSubtreeStream stream_rewrite_tree_element=new RewriteRuleSubtreeStream(adaptor,"rule rewrite_tree_element");
        RewriteRuleSubtreeStream stream_rewrite_tree_atom=new RewriteRuleSubtreeStream(adaptor,"rule rewrite_tree_atom");
        try {
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:397:2: ( '^(' rewrite_tree_atom ( rewrite_tree_element )* ')' -> ^( TREE_BEGIN rewrite_tree_atom ( rewrite_tree_element )* ) )
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:397:4: '^(' rewrite_tree_atom ( rewrite_tree_element )* ')'
            {
            string_literal152=(Token)match(input,TREE_BEGIN,FOLLOW_TREE_BEGIN_in_rewrite_tree2886); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_TREE_BEGIN.add(string_literal152);

            pushFollow(FOLLOW_rewrite_tree_atom_in_rewrite_tree2888);
            rewrite_tree_atom153=rewrite_tree_atom();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_rewrite_tree_atom.add(rewrite_tree_atom153.getTree());
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:397:27: ( rewrite_tree_element )*
            loop70:
            do {
                int alt70=2;
                int LA70_0 = input.LA(1);

                if ( (LA70_0==TREE_BEGIN||(LA70_0>=TOKEN_REF && LA70_0<=ACTION)||LA70_0==RULE_REF||LA70_0==82||LA70_0==93) ) {
                    alt70=1;
                }


                switch (alt70) {
            	case 1 :
            	    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:397:27: rewrite_tree_element
            	    {
            	    pushFollow(FOLLOW_rewrite_tree_element_in_rewrite_tree2890);
            	    rewrite_tree_element154=rewrite_tree_element();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_rewrite_tree_element.add(rewrite_tree_element154.getTree());

            	    }
            	    break;

            	default :
            	    break loop70;
                }
            } while (true);

            char_literal155=(Token)match(input,84,FOLLOW_84_in_rewrite_tree2893); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_84.add(char_literal155);



            // AST REWRITE
            // elements: rewrite_tree_atom, rewrite_tree_element
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 398:3: -> ^( TREE_BEGIN rewrite_tree_atom ( rewrite_tree_element )* )
            {
                // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:398:6: ^( TREE_BEGIN rewrite_tree_atom ( rewrite_tree_element )* )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TREE_BEGIN, "TREE_BEGIN"), root_1);

                adaptor.addChild(root_1, stream_rewrite_tree_atom.nextTree());
                // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:398:37: ( rewrite_tree_element )*
                while ( stream_rewrite_tree_element.hasNext() ) {
                    adaptor.addChild(root_1, stream_rewrite_tree_element.nextTree());

                }
                stream_rewrite_tree_element.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "rewrite_tree"

    public static class rewrite_template_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "rewrite_template"
    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:401:1: rewrite_template : ( id lp= '(' rewrite_template_args ')' (str= DOUBLE_QUOTE_STRING_LITERAL | str= DOUBLE_ANGLE_STRING_LITERAL ) -> ^( TEMPLATE[$lp,\"TEMPLATE\"] id rewrite_template_args $str) | rewrite_template_ref | rewrite_indirect_template_head | ACTION );
    public final ANTLRv3Parser.rewrite_template_return rewrite_template() throws RecognitionException {
        ANTLRv3Parser.rewrite_template_return retval = new ANTLRv3Parser.rewrite_template_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token lp=null;
        Token str=null;
        Token char_literal158=null;
        Token ACTION161=null;
        ANTLRv3Parser.id_return id156 = null;

        ANTLRv3Parser.rewrite_template_args_return rewrite_template_args157 = null;

        ANTLRv3Parser.rewrite_template_ref_return rewrite_template_ref159 = null;

        ANTLRv3Parser.rewrite_indirect_template_head_return rewrite_indirect_template_head160 = null;


        CommonTree lp_tree=null;
        CommonTree str_tree=null;
        CommonTree char_literal158_tree=null;
        CommonTree ACTION161_tree=null;
        RewriteRuleTokenStream stream_DOUBLE_QUOTE_STRING_LITERAL=new RewriteRuleTokenStream(adaptor,"token DOUBLE_QUOTE_STRING_LITERAL");
        RewriteRuleTokenStream stream_82=new RewriteRuleTokenStream(adaptor,"token 82");
        RewriteRuleTokenStream stream_DOUBLE_ANGLE_STRING_LITERAL=new RewriteRuleTokenStream(adaptor,"token DOUBLE_ANGLE_STRING_LITERAL");
        RewriteRuleTokenStream stream_84=new RewriteRuleTokenStream(adaptor,"token 84");
        RewriteRuleSubtreeStream stream_id=new RewriteRuleSubtreeStream(adaptor,"rule id");
        RewriteRuleSubtreeStream stream_rewrite_template_args=new RewriteRuleSubtreeStream(adaptor,"rule rewrite_template_args");
        try {
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:413:2: ( id lp= '(' rewrite_template_args ')' (str= DOUBLE_QUOTE_STRING_LITERAL | str= DOUBLE_ANGLE_STRING_LITERAL ) -> ^( TEMPLATE[$lp,\"TEMPLATE\"] id rewrite_template_args $str) | rewrite_template_ref | rewrite_indirect_template_head | ACTION )
            int alt72=4;
            alt72 = dfa72.predict(input);
            switch (alt72) {
                case 1 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:414:3: id lp= '(' rewrite_template_args ')' (str= DOUBLE_QUOTE_STRING_LITERAL | str= DOUBLE_ANGLE_STRING_LITERAL )
                    {
                    pushFollow(FOLLOW_id_in_rewrite_template2925);
                    id156=id();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_id.add(id156.getTree());
                    lp=(Token)match(input,82,FOLLOW_82_in_rewrite_template2929); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_82.add(lp);

                    pushFollow(FOLLOW_rewrite_template_args_in_rewrite_template2931);
                    rewrite_template_args157=rewrite_template_args();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_rewrite_template_args.add(rewrite_template_args157.getTree());
                    char_literal158=(Token)match(input,84,FOLLOW_84_in_rewrite_template2933); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_84.add(char_literal158);

                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:415:3: (str= DOUBLE_QUOTE_STRING_LITERAL | str= DOUBLE_ANGLE_STRING_LITERAL )
                    int alt71=2;
                    int LA71_0 = input.LA(1);

                    if ( (LA71_0==DOUBLE_QUOTE_STRING_LITERAL) ) {
                        alt71=1;
                    }
                    else if ( (LA71_0==DOUBLE_ANGLE_STRING_LITERAL) ) {
                        alt71=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 71, 0, input);

                        throw nvae;
                    }
                    switch (alt71) {
                        case 1 :
                            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:415:5: str= DOUBLE_QUOTE_STRING_LITERAL
                            {
                            str=(Token)match(input,DOUBLE_QUOTE_STRING_LITERAL,FOLLOW_DOUBLE_QUOTE_STRING_LITERAL_in_rewrite_template2941); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_DOUBLE_QUOTE_STRING_LITERAL.add(str);


                            }
                            break;
                        case 2 :
                            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:415:39: str= DOUBLE_ANGLE_STRING_LITERAL
                            {
                            str=(Token)match(input,DOUBLE_ANGLE_STRING_LITERAL,FOLLOW_DOUBLE_ANGLE_STRING_LITERAL_in_rewrite_template2947); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_DOUBLE_ANGLE_STRING_LITERAL.add(str);


                            }
                            break;

                    }



                    // AST REWRITE
                    // elements: id, str, rewrite_template_args
                    // token labels: str
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleTokenStream stream_str=new RewriteRuleTokenStream(adaptor,"token str",str);
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 416:3: -> ^( TEMPLATE[$lp,\"TEMPLATE\"] id rewrite_template_args $str)
                    {
                        // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:416:6: ^( TEMPLATE[$lp,\"TEMPLATE\"] id rewrite_template_args $str)
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TEMPLATE, lp, "TEMPLATE"), root_1);

                        adaptor.addChild(root_1, stream_id.nextTree());
                        adaptor.addChild(root_1, stream_rewrite_template_args.nextTree());
                        adaptor.addChild(root_1, stream_str.nextNode());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:419:3: rewrite_template_ref
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_rewrite_template_ref_in_rewrite_template2974);
                    rewrite_template_ref159=rewrite_template_ref();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, rewrite_template_ref159.getTree());

                    }
                    break;
                case 3 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:422:3: rewrite_indirect_template_head
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_rewrite_indirect_template_head_in_rewrite_template2983);
                    rewrite_indirect_template_head160=rewrite_indirect_template_head();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, rewrite_indirect_template_head160.getTree());

                    }
                    break;
                case 4 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:425:3: ACTION
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    ACTION161=(Token)match(input,ACTION,FOLLOW_ACTION_in_rewrite_template2992); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ACTION161_tree = (CommonTree)adaptor.create(ACTION161);
                    adaptor.addChild(root_0, ACTION161_tree);
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "rewrite_template"

    public static class rewrite_template_ref_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "rewrite_template_ref"
    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:428:1: rewrite_template_ref : id lp= '(' rewrite_template_args ')' -> ^( TEMPLATE[$lp,\"TEMPLATE\"] id rewrite_template_args ) ;
    public final ANTLRv3Parser.rewrite_template_ref_return rewrite_template_ref() throws RecognitionException {
        ANTLRv3Parser.rewrite_template_ref_return retval = new ANTLRv3Parser.rewrite_template_ref_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token lp=null;
        Token char_literal164=null;
        ANTLRv3Parser.id_return id162 = null;

        ANTLRv3Parser.rewrite_template_args_return rewrite_template_args163 = null;


        CommonTree lp_tree=null;
        CommonTree char_literal164_tree=null;
        RewriteRuleTokenStream stream_82=new RewriteRuleTokenStream(adaptor,"token 82");
        RewriteRuleTokenStream stream_84=new RewriteRuleTokenStream(adaptor,"token 84");
        RewriteRuleSubtreeStream stream_id=new RewriteRuleSubtreeStream(adaptor,"rule id");
        RewriteRuleSubtreeStream stream_rewrite_template_args=new RewriteRuleSubtreeStream(adaptor,"rule rewrite_template_args");
        try {
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:430:2: ( id lp= '(' rewrite_template_args ')' -> ^( TEMPLATE[$lp,\"TEMPLATE\"] id rewrite_template_args ) )
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:430:4: id lp= '(' rewrite_template_args ')'
            {
            pushFollow(FOLLOW_id_in_rewrite_template_ref3005);
            id162=id();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_id.add(id162.getTree());
            lp=(Token)match(input,82,FOLLOW_82_in_rewrite_template_ref3009); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_82.add(lp);

            pushFollow(FOLLOW_rewrite_template_args_in_rewrite_template_ref3011);
            rewrite_template_args163=rewrite_template_args();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_rewrite_template_args.add(rewrite_template_args163.getTree());
            char_literal164=(Token)match(input,84,FOLLOW_84_in_rewrite_template_ref3013); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_84.add(char_literal164);



            // AST REWRITE
            // elements: rewrite_template_args, id
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 431:3: -> ^( TEMPLATE[$lp,\"TEMPLATE\"] id rewrite_template_args )
            {
                // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:431:6: ^( TEMPLATE[$lp,\"TEMPLATE\"] id rewrite_template_args )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TEMPLATE, lp, "TEMPLATE"), root_1);

                adaptor.addChild(root_1, stream_id.nextTree());
                adaptor.addChild(root_1, stream_rewrite_template_args.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "rewrite_template_ref"

    public static class rewrite_indirect_template_head_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "rewrite_indirect_template_head"
    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:434:1: rewrite_indirect_template_head : lp= '(' ACTION ')' '(' rewrite_template_args ')' -> ^( TEMPLATE[$lp,\"TEMPLATE\"] ACTION rewrite_template_args ) ;
    public final ANTLRv3Parser.rewrite_indirect_template_head_return rewrite_indirect_template_head() throws RecognitionException {
        ANTLRv3Parser.rewrite_indirect_template_head_return retval = new ANTLRv3Parser.rewrite_indirect_template_head_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token lp=null;
        Token ACTION165=null;
        Token char_literal166=null;
        Token char_literal167=null;
        Token char_literal169=null;
        ANTLRv3Parser.rewrite_template_args_return rewrite_template_args168 = null;


        CommonTree lp_tree=null;
        CommonTree ACTION165_tree=null;
        CommonTree char_literal166_tree=null;
        CommonTree char_literal167_tree=null;
        CommonTree char_literal169_tree=null;
        RewriteRuleTokenStream stream_82=new RewriteRuleTokenStream(adaptor,"token 82");
        RewriteRuleTokenStream stream_ACTION=new RewriteRuleTokenStream(adaptor,"token ACTION");
        RewriteRuleTokenStream stream_84=new RewriteRuleTokenStream(adaptor,"token 84");
        RewriteRuleSubtreeStream stream_rewrite_template_args=new RewriteRuleSubtreeStream(adaptor,"rule rewrite_template_args");
        try {
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:436:2: (lp= '(' ACTION ')' '(' rewrite_template_args ')' -> ^( TEMPLATE[$lp,\"TEMPLATE\"] ACTION rewrite_template_args ) )
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:436:4: lp= '(' ACTION ')' '(' rewrite_template_args ')'
            {
            lp=(Token)match(input,82,FOLLOW_82_in_rewrite_indirect_template_head3041); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_82.add(lp);

            ACTION165=(Token)match(input,ACTION,FOLLOW_ACTION_in_rewrite_indirect_template_head3043); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ACTION.add(ACTION165);

            char_literal166=(Token)match(input,84,FOLLOW_84_in_rewrite_indirect_template_head3045); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_84.add(char_literal166);

            char_literal167=(Token)match(input,82,FOLLOW_82_in_rewrite_indirect_template_head3047); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_82.add(char_literal167);

            pushFollow(FOLLOW_rewrite_template_args_in_rewrite_indirect_template_head3049);
            rewrite_template_args168=rewrite_template_args();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_rewrite_template_args.add(rewrite_template_args168.getTree());
            char_literal169=(Token)match(input,84,FOLLOW_84_in_rewrite_indirect_template_head3051); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_84.add(char_literal169);



            // AST REWRITE
            // elements: ACTION, rewrite_template_args
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 437:3: -> ^( TEMPLATE[$lp,\"TEMPLATE\"] ACTION rewrite_template_args )
            {
                // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:437:6: ^( TEMPLATE[$lp,\"TEMPLATE\"] ACTION rewrite_template_args )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(TEMPLATE, lp, "TEMPLATE"), root_1);

                adaptor.addChild(root_1, stream_ACTION.nextNode());
                adaptor.addChild(root_1, stream_rewrite_template_args.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "rewrite_indirect_template_head"

    public static class rewrite_template_args_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "rewrite_template_args"
    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:440:1: rewrite_template_args : ( rewrite_template_arg ( ',' rewrite_template_arg )* -> ^( ARGLIST ( rewrite_template_arg )+ ) | -> ARGLIST );
    public final ANTLRv3Parser.rewrite_template_args_return rewrite_template_args() throws RecognitionException {
        ANTLRv3Parser.rewrite_template_args_return retval = new ANTLRv3Parser.rewrite_template_args_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal171=null;
        ANTLRv3Parser.rewrite_template_arg_return rewrite_template_arg170 = null;

        ANTLRv3Parser.rewrite_template_arg_return rewrite_template_arg172 = null;


        CommonTree char_literal171_tree=null;
        RewriteRuleTokenStream stream_81=new RewriteRuleTokenStream(adaptor,"token 81");
        RewriteRuleSubtreeStream stream_rewrite_template_arg=new RewriteRuleSubtreeStream(adaptor,"rule rewrite_template_arg");
        try {
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:441:2: ( rewrite_template_arg ( ',' rewrite_template_arg )* -> ^( ARGLIST ( rewrite_template_arg )+ ) | -> ARGLIST )
            int alt74=2;
            int LA74_0 = input.LA(1);

            if ( (LA74_0==TOKEN_REF||LA74_0==RULE_REF) ) {
                alt74=1;
            }
            else if ( (LA74_0==84) ) {
                alt74=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 74, 0, input);

                throw nvae;
            }
            switch (alt74) {
                case 1 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:441:4: rewrite_template_arg ( ',' rewrite_template_arg )*
                    {
                    pushFollow(FOLLOW_rewrite_template_arg_in_rewrite_template_args3075);
                    rewrite_template_arg170=rewrite_template_arg();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_rewrite_template_arg.add(rewrite_template_arg170.getTree());
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:441:25: ( ',' rewrite_template_arg )*
                    loop73:
                    do {
                        int alt73=2;
                        int LA73_0 = input.LA(1);

                        if ( (LA73_0==81) ) {
                            alt73=1;
                        }


                        switch (alt73) {
                    	case 1 :
                    	    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:441:26: ',' rewrite_template_arg
                    	    {
                    	    char_literal171=(Token)match(input,81,FOLLOW_81_in_rewrite_template_args3078); if (state.failed) return retval; 
                    	    if ( state.backtracking==0 ) stream_81.add(char_literal171);

                    	    pushFollow(FOLLOW_rewrite_template_arg_in_rewrite_template_args3080);
                    	    rewrite_template_arg172=rewrite_template_arg();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_rewrite_template_arg.add(rewrite_template_arg172.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop73;
                        }
                    } while (true);



                    // AST REWRITE
                    // elements: rewrite_template_arg
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 442:3: -> ^( ARGLIST ( rewrite_template_arg )+ )
                    {
                        // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:442:6: ^( ARGLIST ( rewrite_template_arg )+ )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ARGLIST, "ARGLIST"), root_1);

                        if ( !(stream_rewrite_template_arg.hasNext()) ) {
                            throw new RewriteEarlyExitException();
                        }
                        while ( stream_rewrite_template_arg.hasNext() ) {
                            adaptor.addChild(root_1, stream_rewrite_template_arg.nextTree());

                        }
                        stream_rewrite_template_arg.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:443:4: 
                    {

                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 443:4: -> ARGLIST
                    {
                        adaptor.addChild(root_0, (CommonTree)adaptor.create(ARGLIST, "ARGLIST"));

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "rewrite_template_args"

    public static class rewrite_template_arg_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "rewrite_template_arg"
    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:446:1: rewrite_template_arg : id '=' ACTION -> ^( ARG[$id.start] id ACTION ) ;
    public final ANTLRv3Parser.rewrite_template_arg_return rewrite_template_arg() throws RecognitionException {
        ANTLRv3Parser.rewrite_template_arg_return retval = new ANTLRv3Parser.rewrite_template_arg_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal174=null;
        Token ACTION175=null;
        ANTLRv3Parser.id_return id173 = null;


        CommonTree char_literal174_tree=null;
        CommonTree ACTION175_tree=null;
        RewriteRuleTokenStream stream_71=new RewriteRuleTokenStream(adaptor,"token 71");
        RewriteRuleTokenStream stream_ACTION=new RewriteRuleTokenStream(adaptor,"token ACTION");
        RewriteRuleSubtreeStream stream_id=new RewriteRuleSubtreeStream(adaptor,"rule id");
        try {
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:447:2: ( id '=' ACTION -> ^( ARG[$id.start] id ACTION ) )
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:447:6: id '=' ACTION
            {
            pushFollow(FOLLOW_id_in_rewrite_template_arg3113);
            id173=id();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_id.add(id173.getTree());
            char_literal174=(Token)match(input,71,FOLLOW_71_in_rewrite_template_arg3115); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_71.add(char_literal174);

            ACTION175=(Token)match(input,ACTION,FOLLOW_ACTION_in_rewrite_template_arg3117); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ACTION.add(ACTION175);



            // AST REWRITE
            // elements: ACTION, id
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 447:20: -> ^( ARG[$id.start] id ACTION )
            {
                // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:447:23: ^( ARG[$id.start] id ACTION )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot((CommonTree)adaptor.create(ARG, (id173!=null?((Token)id173.start):null)), root_1);

                adaptor.addChild(root_1, stream_id.nextTree());
                adaptor.addChild(root_1, stream_ACTION.nextNode());

                adaptor.addChild(root_0, root_1);
                }

            }

            retval.tree = root_0;}
            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "rewrite_template_arg"

    public static class id_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "id"
    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:450:1: id : ( TOKEN_REF -> ID[$TOKEN_REF] | RULE_REF -> ID[$RULE_REF] );
    public final ANTLRv3Parser.id_return id() throws RecognitionException {
        ANTLRv3Parser.id_return retval = new ANTLRv3Parser.id_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token TOKEN_REF176=null;
        Token RULE_REF177=null;

        CommonTree TOKEN_REF176_tree=null;
        CommonTree RULE_REF177_tree=null;
        RewriteRuleTokenStream stream_RULE_REF=new RewriteRuleTokenStream(adaptor,"token RULE_REF");
        RewriteRuleTokenStream stream_TOKEN_REF=new RewriteRuleTokenStream(adaptor,"token TOKEN_REF");

        try {
            // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:450:4: ( TOKEN_REF -> ID[$TOKEN_REF] | RULE_REF -> ID[$RULE_REF] )
            int alt75=2;
            int LA75_0 = input.LA(1);

            if ( (LA75_0==TOKEN_REF) ) {
                alt75=1;
            }
            else if ( (LA75_0==RULE_REF) ) {
                alt75=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 75, 0, input);

                throw nvae;
            }
            switch (alt75) {
                case 1 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:450:6: TOKEN_REF
                    {
                    TOKEN_REF176=(Token)match(input,TOKEN_REF,FOLLOW_TOKEN_REF_in_id3138); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_TOKEN_REF.add(TOKEN_REF176);



                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 450:16: -> ID[$TOKEN_REF]
                    {
                        adaptor.addChild(root_0, (CommonTree)adaptor.create(ID, TOKEN_REF176));

                    }

                    retval.tree = root_0;}
                    }
                    break;
                case 2 :
                    // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:451:4: RULE_REF
                    {
                    RULE_REF177=(Token)match(input,RULE_REF,FOLLOW_RULE_REF_in_id3148); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_RULE_REF.add(RULE_REF177);



                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 451:14: -> ID[$RULE_REF]
                    {
                        adaptor.addChild(root_0, (CommonTree)adaptor.create(ID, RULE_REF177));

                    }

                    retval.tree = root_0;}
                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "id"

    // $ANTLR start synpred1_ANTLRv3
    public final void synpred1_ANTLRv3_fragment() throws RecognitionException {   
        // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:350:4: ( rewrite_template )
        // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:350:4: rewrite_template
        {
        pushFollow(FOLLOW_rewrite_template_in_synpred1_ANTLRv32562);
        rewrite_template();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred1_ANTLRv3

    // $ANTLR start synpred2_ANTLRv3
    public final void synpred2_ANTLRv3_fragment() throws RecognitionException {   
        // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:351:4: ( rewrite_tree_alternative )
        // /Volumes/Mercurial/web-main/contrib/o.n.antlr.editor/src/org/netbeans/modules/antlr/editor/ANTLRv3.g:351:4: rewrite_tree_alternative
        {
        pushFollow(FOLLOW_rewrite_tree_alternative_in_synpred2_ANTLRv32567);
        rewrite_tree_alternative();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred2_ANTLRv3

    // Delegated rules

    public final boolean synpred2_ANTLRv3() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred2_ANTLRv3_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred1_ANTLRv3() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred1_ANTLRv3_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA46 dfa46 = new DFA46(this);
    protected DFA64 dfa64 = new DFA64(this);
    protected DFA67 dfa67 = new DFA67(this);
    protected DFA72 dfa72 = new DFA72(this);
    static final String DFA46_eotS =
        "\14\uffff";
    static final String DFA46_eofS =
        "\14\uffff";
    static final String DFA46_minS =
        "\3\40\5\uffff\2\52\2\uffff";
    static final String DFA46_maxS =
        "\3\134\5\uffff\2\134\2\uffff";
    static final String DFA46_acceptS =
        "\3\uffff\1\3\1\4\1\5\1\6\1\7\2\uffff\1\1\1\2";
    static final String DFA46_specialS =
        "\14\uffff}>";
    static final String[] DFA46_transitionS = {
            "\1\6\4\uffff\1\7\4\uffff\1\1\2\3\1\5\3\uffff\1\2\40\uffff\1"+
            "\4\6\uffff\1\3\2\uffff\1\3",
            "\1\3\4\uffff\4\3\1\uffff\4\3\2\uffff\2\3\23\uffff\1\3\1\uffff"+
            "\1\10\2\uffff\1\3\7\uffff\3\3\2\uffff\1\11\1\uffff\4\3",
            "\1\3\4\uffff\4\3\1\uffff\4\3\2\uffff\2\3\23\uffff\1\3\1\uffff"+
            "\1\10\2\uffff\1\3\7\uffff\3\3\2\uffff\1\11\1\uffff\4\3",
            "",
            "",
            "",
            "",
            "",
            "\3\12\4\uffff\1\12\40\uffff\1\13\6\uffff\1\12\2\uffff\1\12",
            "\3\12\4\uffff\1\12\40\uffff\1\13\6\uffff\1\12\2\uffff\1\12",
            "",
            ""
    };

    static final short[] DFA46_eot = DFA.unpackEncodedString(DFA46_eotS);
    static final short[] DFA46_eof = DFA.unpackEncodedString(DFA46_eofS);
    static final char[] DFA46_min = DFA.unpackEncodedStringToUnsignedChars(DFA46_minS);
    static final char[] DFA46_max = DFA.unpackEncodedStringToUnsignedChars(DFA46_maxS);
    static final short[] DFA46_accept = DFA.unpackEncodedString(DFA46_acceptS);
    static final short[] DFA46_special = DFA.unpackEncodedString(DFA46_specialS);
    static final short[][] DFA46_transition;

    static {
        int numStates = DFA46_transitionS.length;
        DFA46_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA46_transition[i] = DFA.unpackEncodedString(DFA46_transitionS[i]);
        }
    }

    class DFA46 extends DFA {

        public DFA46(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 46;
            this.eot = DFA46_eot;
            this.eof = DFA46_eof;
            this.min = DFA46_min;
            this.max = DFA46_max;
            this.accept = DFA46_accept;
            this.special = DFA46_special;
            this.transition = DFA46_transition;
        }
        public String getDescription() {
            return "233:1: elementNoOptionSpec : ( id (labelOp= '=' | labelOp= '+=' ) atom ( ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] ^( $labelOp id atom ) EOA[\"EOA\"] ) EOB[\"EOB\"] ) ) | -> ^( $labelOp id atom ) ) | id (labelOp= '=' | labelOp= '+=' ) block ( ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] ^( $labelOp id block ) EOA[\"EOA\"] ) EOB[\"EOB\"] ) ) | -> ^( $labelOp id block ) ) | atom ( ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] atom EOA[\"EOA\"] ) EOB[\"EOB\"] ) ) | -> atom ) | ebnf | ACTION | SEMPRED ( '=>' -> GATED_SEMPRED | -> SEMPRED ) | treeSpec ( ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] treeSpec EOA[\"EOA\"] ) EOB[\"EOB\"] ) ) | -> treeSpec ) );";
        }
    }
    static final String DFA64_eotS =
        "\15\uffff";
    static final String DFA64_eofS =
        "\15\uffff";
    static final String DFA64_minS =
        "\4\45\1\0\2\uffff\2\45\1\uffff\2\45\1\112";
    static final String DFA64_maxS =
        "\4\135\1\0\2\uffff\2\135\1\uffff\2\135\1\133";
    static final String DFA64_acceptS =
        "\5\uffff\1\2\1\3\2\uffff\1\1\3\uffff";
    static final String DFA64_specialS =
        "\4\uffff\1\0\10\uffff}>";
    static final String[] DFA64_transitionS = {
            "\1\5\2\uffff\1\6\1\uffff\1\1\2\5\1\4\3\uffff\1\2\23\uffff\1"+
            "\6\14\uffff\1\3\2\6\10\uffff\1\5",
            "\1\5\2\uffff\1\5\1\uffff\4\5\2\uffff\2\5\23\uffff\1\5\4\uffff"+
            "\1\5\7\uffff\1\7\2\5\5\uffff\2\5\1\uffff\1\5",
            "\1\5\2\uffff\1\5\1\uffff\4\5\3\uffff\1\5\23\uffff\1\5\4\uffff"+
            "\1\5\7\uffff\1\7\2\5\5\uffff\2\5\1\uffff\1\5",
            "\1\5\4\uffff\3\5\1\10\3\uffff\1\5\40\uffff\1\5\12\uffff\1\5",
            "\1\uffff",
            "",
            "",
            "\1\5\4\uffff\1\12\3\5\3\uffff\1\13\40\uffff\1\5\1\uffff\1\11"+
            "\10\uffff\1\5",
            "\1\5\4\uffff\4\5\3\uffff\1\5\30\uffff\1\5\7\uffff\1\5\1\uffff"+
            "\1\14\5\uffff\2\5\1\uffff\1\5",
            "",
            "\1\5\4\uffff\4\5\2\uffff\2\5\25\uffff\1\11\2\uffff\1\5\7\uffff"+
            "\1\5\1\uffff\1\5\5\uffff\2\5\1\uffff\1\5",
            "\1\5\4\uffff\4\5\3\uffff\1\5\25\uffff\1\11\2\uffff\1\5\7\uffff"+
            "\1\5\1\uffff\1\5\5\uffff\2\5\1\uffff\1\5",
            "\1\5\7\uffff\1\11\7\uffff\2\5"
    };

    static final short[] DFA64_eot = DFA.unpackEncodedString(DFA64_eotS);
    static final short[] DFA64_eof = DFA.unpackEncodedString(DFA64_eofS);
    static final char[] DFA64_min = DFA.unpackEncodedStringToUnsignedChars(DFA64_minS);
    static final char[] DFA64_max = DFA.unpackEncodedStringToUnsignedChars(DFA64_maxS);
    static final short[] DFA64_accept = DFA.unpackEncodedString(DFA64_acceptS);
    static final short[] DFA64_special = DFA.unpackEncodedString(DFA64_specialS);
    static final short[][] DFA64_transition;

    static {
        int numStates = DFA64_transitionS.length;
        DFA64_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA64_transition[i] = DFA.unpackEncodedString(DFA64_transitionS[i]);
        }
    }

    class DFA64 extends DFA {

        public DFA64(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 64;
            this.eot = DFA64_eot;
            this.eof = DFA64_eof;
            this.min = DFA64_min;
            this.max = DFA64_max;
            this.accept = DFA64_accept;
            this.special = DFA64_special;
            this.transition = DFA64_transition;
        }
        public String getDescription() {
            return "348:1: rewrite_alternative options {backtrack=true; } : ( rewrite_template | rewrite_tree_alternative | -> ^( ALT[\"ALT\"] EPSILON[\"EPSILON\"] EOA[\"EOA\"] ) );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA64_4 = input.LA(1);

                         
                        int index64_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred1_ANTLRv3()) ) {s = 9;}

                        else if ( (synpred2_ANTLRv3()) ) {s = 5;}

                         
                        input.seek(index64_4);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 64, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA67_eotS =
        "\16\uffff";
    static final String DFA67_eofS =
        "\1\uffff\4\11\1\uffff\1\11\4\uffff\3\11";
    static final String DFA67_minS =
        "\5\45\1\52\1\45\4\uffff\3\45";
    static final String DFA67_maxS =
        "\5\135\1\61\1\135\4\uffff\3\135";
    static final String DFA67_acceptS =
        "\7\uffff\1\3\1\4\1\1\1\2\3\uffff";
    static final String DFA67_specialS =
        "\16\uffff}>";
    static final String[] DFA67_transitionS = {
            "\1\7\4\uffff\1\2\1\4\1\1\1\6\3\uffff\1\3\40\uffff\1\10\12\uffff"+
            "\1\5",
            "\1\11\2\uffff\1\11\1\uffff\4\11\3\uffff\1\11\23\uffff\1\11"+
            "\4\uffff\1\12\7\uffff\3\11\5\uffff\2\12\1\uffff\1\11",
            "\1\11\2\uffff\1\11\1\uffff\4\11\2\uffff\1\13\1\11\23\uffff"+
            "\1\11\4\uffff\1\12\7\uffff\3\11\5\uffff\2\12\1\uffff\1\11",
            "\1\11\2\uffff\1\11\1\uffff\4\11\3\uffff\1\11\23\uffff\1\11"+
            "\4\uffff\1\12\7\uffff\3\11\5\uffff\2\12\1\uffff\1\11",
            "\1\11\2\uffff\1\11\1\uffff\4\11\3\uffff\1\11\23\uffff\1\11"+
            "\4\uffff\1\12\7\uffff\3\11\5\uffff\2\12\1\uffff\1\11",
            "\1\14\6\uffff\1\15",
            "\1\11\2\uffff\1\11\1\uffff\4\11\3\uffff\1\11\23\uffff\1\11"+
            "\4\uffff\1\12\7\uffff\3\11\5\uffff\2\12\1\uffff\1\11",
            "",
            "",
            "",
            "",
            "\1\11\2\uffff\1\11\1\uffff\4\11\3\uffff\1\11\23\uffff\1\11"+
            "\4\uffff\1\12\7\uffff\3\11\5\uffff\2\12\1\uffff\1\11",
            "\1\11\2\uffff\1\11\1\uffff\4\11\3\uffff\1\11\23\uffff\1\11"+
            "\4\uffff\1\12\7\uffff\3\11\5\uffff\2\12\1\uffff\1\11",
            "\1\11\2\uffff\1\11\1\uffff\4\11\3\uffff\1\11\23\uffff\1\11"+
            "\4\uffff\1\12\7\uffff\3\11\5\uffff\2\12\1\uffff\1\11"
    };

    static final short[] DFA67_eot = DFA.unpackEncodedString(DFA67_eotS);
    static final short[] DFA67_eof = DFA.unpackEncodedString(DFA67_eofS);
    static final char[] DFA67_min = DFA.unpackEncodedStringToUnsignedChars(DFA67_minS);
    static final char[] DFA67_max = DFA.unpackEncodedStringToUnsignedChars(DFA67_maxS);
    static final short[] DFA67_accept = DFA.unpackEncodedString(DFA67_acceptS);
    static final short[] DFA67_special = DFA.unpackEncodedString(DFA67_specialS);
    static final short[][] DFA67_transition;

    static {
        int numStates = DFA67_transitionS.length;
        DFA67_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA67_transition[i] = DFA.unpackEncodedString(DFA67_transitionS[i]);
        }
    }

    class DFA67 extends DFA {

        public DFA67(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 67;
            this.eot = DFA67_eot;
            this.eof = DFA67_eof;
            this.min = DFA67_min;
            this.max = DFA67_max;
            this.accept = DFA67_accept;
            this.special = DFA67_special;
            this.transition = DFA67_transition;
        }
        public String getDescription() {
            return "364:1: rewrite_tree_element : ( rewrite_tree_atom | rewrite_tree_atom ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] rewrite_tree_atom EOA[\"EOA\"] ) EOB[\"EOB\"] ) ) | rewrite_tree ( ebnfSuffix -> ^( ebnfSuffix ^( BLOCK[\"BLOCK\"] ^( ALT[\"ALT\"] rewrite_tree EOA[\"EOA\"] ) EOB[\"EOB\"] ) ) | -> rewrite_tree ) | rewrite_tree_ebnf );";
        }
    }
    static final String DFA72_eotS =
        "\22\uffff";
    static final String DFA72_eofS =
        "\10\uffff\1\13\11\uffff";
    static final String DFA72_minS =
        "\1\52\2\122\2\uffff\1\52\2\107\1\50\1\55\2\uffff\1\121\1\52\2\107"+
        "\1\55\1\121";
    static final String DFA72_maxS =
        "\3\122\2\uffff\1\124\2\107\1\124\1\55\2\uffff\1\124\1\61\2\107\1"+
        "\55\1\124";
    static final String DFA72_acceptS =
        "\3\uffff\1\3\1\4\5\uffff\1\1\1\2\6\uffff";
    static final String DFA72_specialS =
        "\22\uffff}>";
    static final String[] DFA72_transitionS = {
            "\1\1\2\uffff\1\4\3\uffff\1\2\40\uffff\1\3",
            "\1\5",
            "\1\5",
            "",
            "",
            "\1\6\6\uffff\1\7\42\uffff\1\10",
            "\1\11",
            "\1\11",
            "\1\13\11\uffff\2\12\21\uffff\1\13\15\uffff\2\13",
            "\1\14",
            "",
            "",
            "\1\15\2\uffff\1\10",
            "\1\16\6\uffff\1\17",
            "\1\20",
            "\1\20",
            "\1\21",
            "\1\15\2\uffff\1\10"
    };

    static final short[] DFA72_eot = DFA.unpackEncodedString(DFA72_eotS);
    static final short[] DFA72_eof = DFA.unpackEncodedString(DFA72_eofS);
    static final char[] DFA72_min = DFA.unpackEncodedStringToUnsignedChars(DFA72_minS);
    static final char[] DFA72_max = DFA.unpackEncodedStringToUnsignedChars(DFA72_maxS);
    static final short[] DFA72_accept = DFA.unpackEncodedString(DFA72_acceptS);
    static final short[] DFA72_special = DFA.unpackEncodedString(DFA72_specialS);
    static final short[][] DFA72_transition;

    static {
        int numStates = DFA72_transitionS.length;
        DFA72_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA72_transition[i] = DFA.unpackEncodedString(DFA72_transitionS[i]);
        }
    }

    class DFA72 extends DFA {

        public DFA72(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 72;
            this.eot = DFA72_eot;
            this.eof = DFA72_eof;
            this.min = DFA72_min;
            this.max = DFA72_max;
            this.accept = DFA72_accept;
            this.special = DFA72_special;
            this.transition = DFA72_transition;
        }
        public String getDescription() {
            return "401:1: rewrite_template : ( id lp= '(' rewrite_template_args ')' (str= DOUBLE_QUOTE_STRING_LITERAL | str= DOUBLE_ANGLE_STRING_LITERAL ) -> ^( TEMPLATE[$lp,\"TEMPLATE\"] id rewrite_template_args $str) | rewrite_template_ref | rewrite_indirect_template_head | ACTION );";
        }
    }
 

    public static final BitSet FOLLOW_DOC_COMMENT_in_grammarDef343 = new BitSet(new long[]{0x0000000000000000L,0x000000000000001EL});
    public static final BitSet FOLLOW_65_in_grammarDef353 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_66_in_grammarDef371 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_67_in_grammarDef387 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_grammarDef428 = new BitSet(new long[]{0x0002040000000000L});
    public static final BitSet FOLLOW_id_in_grammarDef430 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_grammarDef432 = new BitSet(new long[]{0x0002461080000010L,0x0000000000003900L});
    public static final BitSet FOLLOW_optionsSpec_in_grammarDef434 = new BitSet(new long[]{0x0002461080000010L,0x0000000000003900L});
    public static final BitSet FOLLOW_tokensSpec_in_grammarDef437 = new BitSet(new long[]{0x0002461080000010L,0x0000000000003900L});
    public static final BitSet FOLLOW_attrScope_in_grammarDef440 = new BitSet(new long[]{0x0002461080000010L,0x0000000000003900L});
    public static final BitSet FOLLOW_action_in_grammarDef443 = new BitSet(new long[]{0x0002461080000010L,0x0000000000003900L});
    public static final BitSet FOLLOW_rule_in_grammarDef451 = new BitSet(new long[]{0x0002461080000010L,0x0000000000003900L});
    public static final BitSet FOLLOW_EOF_in_grammarDef459 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOKENS_in_tokensSpec520 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_tokenSpec_in_tokensSpec522 = new BitSet(new long[]{0x0000040000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_70_in_tokensSpec525 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOKEN_REF_in_tokenSpec545 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000A0L});
    public static final BitSet FOLLOW_71_in_tokenSpec551 = new BitSet(new long[]{0x0000180000000000L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_tokenSpec556 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_CHAR_LITERAL_in_tokenSpec560 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_tokenSpec599 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SCOPE_in_attrScope610 = new BitSet(new long[]{0x0002040000000000L});
    public static final BitSet FOLLOW_id_in_attrScope612 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_ACTION_in_attrScope614 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_72_in_action637 = new BitSet(new long[]{0x0002040000000000L,0x0000000000000006L});
    public static final BitSet FOLLOW_actionScopeName_in_action640 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_73_in_action642 = new BitSet(new long[]{0x0002040000000000L});
    public static final BitSet FOLLOW_id_in_action646 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_ACTION_in_action648 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_actionScopeName674 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_actionScopeName681 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_66_in_actionScopeName698 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OPTIONS_in_optionsSpec714 = new BitSet(new long[]{0x0002040000000000L});
    public static final BitSet FOLLOW_option_in_optionsSpec717 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_optionsSpec719 = new BitSet(new long[]{0x0002040000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_70_in_optionsSpec723 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_option748 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_option750 = new BitSet(new long[]{0x00029C0000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_optionValue_in_option752 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_optionValue781 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_optionValue791 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHAR_LITERAL_in_optionValue801 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_optionValue811 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_74_in_optionValue821 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOC_COMMENT_in_rule846 = new BitSet(new long[]{0x0002041000000000L,0x0000000000003800L});
    public static final BitSet FOLLOW_75_in_rule856 = new BitSet(new long[]{0x0002040000000000L});
    public static final BitSet FOLLOW_76_in_rule858 = new BitSet(new long[]{0x0002040000000000L});
    public static final BitSet FOLLOW_77_in_rule860 = new BitSet(new long[]{0x0002040000000000L});
    public static final BitSet FOLLOW_FRAGMENT_in_rule862 = new BitSet(new long[]{0x0002040000000000L});
    public static final BitSet FOLLOW_id_in_rule870 = new BitSet(new long[]{0x0001408080000000L,0x000000000001C100L});
    public static final BitSet FOLLOW_BANG_in_rule876 = new BitSet(new long[]{0x0001400080000000L,0x000000000001C100L});
    public static final BitSet FOLLOW_ARG_ACTION_in_rule885 = new BitSet(new long[]{0x0000400080000000L,0x000000000001C100L});
    public static final BitSet FOLLOW_78_in_rule894 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_ARG_ACTION_in_rule898 = new BitSet(new long[]{0x0000400080000000L,0x0000000000018100L});
    public static final BitSet FOLLOW_throwsSpec_in_rule906 = new BitSet(new long[]{0x0000400080000000L,0x0000000000008100L});
    public static final BitSet FOLLOW_optionsSpec_in_rule909 = new BitSet(new long[]{0x0000000080000000L,0x0000000000008100L});
    public static final BitSet FOLLOW_ruleScopeSpec_in_rule912 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008100L});
    public static final BitSet FOLLOW_ruleAction_in_rule915 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008100L});
    public static final BitSet FOLLOW_79_in_rule920 = new BitSet(new long[]{0x00023D2100000000L,0x00000000120C0000L});
    public static final BitSet FOLLOW_altList_in_rule922 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_rule924 = new BitSet(new long[]{0x0000000000000002L,0x0000000000600000L});
    public static final BitSet FOLLOW_exceptionGroup_in_rule928 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_72_in_ruleAction1030 = new BitSet(new long[]{0x0002040000000000L});
    public static final BitSet FOLLOW_id_in_ruleAction1032 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_ACTION_in_ruleAction1034 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_80_in_throwsSpec1055 = new BitSet(new long[]{0x0002040000000000L});
    public static final BitSet FOLLOW_id_in_throwsSpec1057 = new BitSet(new long[]{0x0000000000000002L,0x0000000000020000L});
    public static final BitSet FOLLOW_81_in_throwsSpec1061 = new BitSet(new long[]{0x0002040000000000L});
    public static final BitSet FOLLOW_id_in_throwsSpec1063 = new BitSet(new long[]{0x0000000000000002L,0x0000000000020000L});
    public static final BitSet FOLLOW_SCOPE_in_ruleScopeSpec1086 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_ACTION_in_ruleScopeSpec1088 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SCOPE_in_ruleScopeSpec1101 = new BitSet(new long[]{0x0002040000000000L});
    public static final BitSet FOLLOW_id_in_ruleScopeSpec1103 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020020L});
    public static final BitSet FOLLOW_81_in_ruleScopeSpec1106 = new BitSet(new long[]{0x0002040000000000L});
    public static final BitSet FOLLOW_id_in_ruleScopeSpec1108 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020020L});
    public static final BitSet FOLLOW_69_in_ruleScopeSpec1112 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SCOPE_in_ruleScopeSpec1126 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_ACTION_in_ruleScopeSpec1128 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_SCOPE_in_ruleScopeSpec1132 = new BitSet(new long[]{0x0002040000000000L});
    public static final BitSet FOLLOW_id_in_ruleScopeSpec1134 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020020L});
    public static final BitSet FOLLOW_81_in_ruleScopeSpec1137 = new BitSet(new long[]{0x0002040000000000L});
    public static final BitSet FOLLOW_id_in_ruleScopeSpec1139 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020020L});
    public static final BitSet FOLLOW_69_in_ruleScopeSpec1143 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_82_in_block1175 = new BitSet(new long[]{0x00027D2100000000L,0x00000000121C8000L});
    public static final BitSet FOLLOW_optionsSpec_in_block1184 = new BitSet(new long[]{0x0000000000000000L,0x0000000000008000L});
    public static final BitSet FOLLOW_79_in_block1188 = new BitSet(new long[]{0x00023D2100000000L,0x00000000121C0000L});
    public static final BitSet FOLLOW_alternative_in_block1197 = new BitSet(new long[]{0x0000010000000000L,0x0000000000180000L});
    public static final BitSet FOLLOW_rewrite_in_block1199 = new BitSet(new long[]{0x0000000000000000L,0x0000000000180000L});
    public static final BitSet FOLLOW_83_in_block1203 = new BitSet(new long[]{0x00023D2100000000L,0x00000000121C0000L});
    public static final BitSet FOLLOW_alternative_in_block1207 = new BitSet(new long[]{0x0000010000000000L,0x0000000000180000L});
    public static final BitSet FOLLOW_rewrite_in_block1209 = new BitSet(new long[]{0x0000000000000000L,0x0000000000180000L});
    public static final BitSet FOLLOW_84_in_block1224 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_alternative_in_altList1281 = new BitSet(new long[]{0x0000010000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_rewrite_in_altList1283 = new BitSet(new long[]{0x0000000000000002L,0x0000000000080000L});
    public static final BitSet FOLLOW_83_in_altList1287 = new BitSet(new long[]{0x00023D2100000000L,0x00000000120C0000L});
    public static final BitSet FOLLOW_alternative_in_altList1291 = new BitSet(new long[]{0x0000010000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_rewrite_in_altList1293 = new BitSet(new long[]{0x0000000000000002L,0x0000000000080000L});
    public static final BitSet FOLLOW_element_in_alternative1341 = new BitSet(new long[]{0x00023C2100000002L,0x0000000012040000L});
    public static final BitSet FOLLOW_exceptionHandler_in_exceptionGroup1392 = new BitSet(new long[]{0x0000000000000002L,0x0000000000600000L});
    public static final BitSet FOLLOW_finallyClause_in_exceptionGroup1399 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_finallyClause_in_exceptionGroup1407 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_85_in_exceptionHandler1427 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_ARG_ACTION_in_exceptionHandler1429 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_ACTION_in_exceptionHandler1431 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_86_in_finallyClause1461 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_ACTION_in_finallyClause1463 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_elementNoOptionSpec_in_element1485 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_elementNoOptionSpec1496 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800080L});
    public static final BitSet FOLLOW_71_in_elementNoOptionSpec1501 = new BitSet(new long[]{0x00021C0000000000L,0x0000000012000000L});
    public static final BitSet FOLLOW_87_in_elementNoOptionSpec1505 = new BitSet(new long[]{0x00021C0000000000L,0x0000000012000000L});
    public static final BitSet FOLLOW_atom_in_elementNoOptionSpec1508 = new BitSet(new long[]{0x0000000000000002L,0x000000000C000400L});
    public static final BitSet FOLLOW_ebnfSuffix_in_elementNoOptionSpec1514 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_elementNoOptionSpec1573 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800080L});
    public static final BitSet FOLLOW_71_in_elementNoOptionSpec1578 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_87_in_elementNoOptionSpec1582 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_block_in_elementNoOptionSpec1585 = new BitSet(new long[]{0x0000000000000002L,0x000000000C000400L});
    public static final BitSet FOLLOW_ebnfSuffix_in_elementNoOptionSpec1591 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atom_in_elementNoOptionSpec1650 = new BitSet(new long[]{0x0000000000000002L,0x000000000C000400L});
    public static final BitSet FOLLOW_ebnfSuffix_in_elementNoOptionSpec1656 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ebnf_in_elementNoOptionSpec1702 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ACTION_in_elementNoOptionSpec1709 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SEMPRED_in_elementNoOptionSpec1716 = new BitSet(new long[]{0x0000000000000002L,0x0000000001000000L});
    public static final BitSet FOLLOW_88_in_elementNoOptionSpec1720 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_treeSpec_in_elementNoOptionSpec1739 = new BitSet(new long[]{0x0000000000000002L,0x000000000C000400L});
    public static final BitSet FOLLOW_ebnfSuffix_in_elementNoOptionSpec1745 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_range_in_atom1797 = new BitSet(new long[]{0x000000C000000002L});
    public static final BitSet FOLLOW_ROOT_in_atom1804 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BANG_in_atom1808 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_terminal_in_atom1836 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_notSet_in_atom1844 = new BitSet(new long[]{0x000000C000000002L});
    public static final BitSet FOLLOW_ROOT_in_atom1851 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BANG_in_atom1855 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_REF_in_atom1883 = new BitSet(new long[]{0x000100C000000002L});
    public static final BitSet FOLLOW_ARG_ACTION_in_atom1889 = new BitSet(new long[]{0x000000C000000002L});
    public static final BitSet FOLLOW_ROOT_in_atom1899 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BANG_in_atom1903 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_89_in_notSet1986 = new BitSet(new long[]{0x00001C0000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_notTerminal_in_notSet1992 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_block_in_notSet2006 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TREE_BEGIN_in_treeSpec2030 = new BitSet(new long[]{0x00023C2100000000L,0x0000000012040000L});
    public static final BitSet FOLLOW_element_in_treeSpec2032 = new BitSet(new long[]{0x00023C2100000000L,0x0000000012040000L});
    public static final BitSet FOLLOW_element_in_treeSpec2036 = new BitSet(new long[]{0x00023C2100000000L,0x0000000012140000L});
    public static final BitSet FOLLOW_84_in_treeSpec2041 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_block_in_ebnf2073 = new BitSet(new long[]{0x0000000000000002L,0x000000000D000400L});
    public static final BitSet FOLLOW_90_in_ebnf2081 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_74_in_ebnf2098 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_91_in_ebnf2115 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_88_in_ebnf2132 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHAR_LITERAL_in_range2215 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_RANGE_in_range2217 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_CHAR_LITERAL_in_range2221 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHAR_LITERAL_in_terminal2252 = new BitSet(new long[]{0x000000C000000002L});
    public static final BitSet FOLLOW_TOKEN_REF_in_terminal2274 = new BitSet(new long[]{0x000100C000000002L});
    public static final BitSet FOLLOW_ARG_ACTION_in_terminal2281 = new BitSet(new long[]{0x000000C000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_terminal2320 = new BitSet(new long[]{0x000000C000000002L});
    public static final BitSet FOLLOW_92_in_terminal2335 = new BitSet(new long[]{0x000000C000000002L});
    public static final BitSet FOLLOW_ROOT_in_terminal2356 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BANG_in_terminal2377 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_notTerminal0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_90_in_ebnfSuffix2437 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_74_in_ebnfSuffix2449 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_91_in_ebnfSuffix2462 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REWRITE_in_rewrite2491 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_SEMPRED_in_rewrite2495 = new BitSet(new long[]{0x00023D2000000000L,0x0000000020040000L});
    public static final BitSet FOLLOW_rewrite_alternative_in_rewrite2499 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_REWRITE_in_rewrite2507 = new BitSet(new long[]{0x00023C2000000000L,0x0000000020040000L});
    public static final BitSet FOLLOW_rewrite_alternative_in_rewrite2511 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rewrite_template_in_rewrite_alternative2562 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rewrite_tree_alternative_in_rewrite_alternative2567 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_82_in_rewrite_tree_block2609 = new BitSet(new long[]{0x00023C2000000000L,0x0000000020040000L});
    public static final BitSet FOLLOW_rewrite_tree_alternative_in_rewrite_tree_block2611 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_84_in_rewrite_tree_block2613 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rewrite_tree_element_in_rewrite_tree_alternative2647 = new BitSet(new long[]{0x00023C2000000002L,0x0000000020040000L});
    public static final BitSet FOLLOW_rewrite_tree_atom_in_rewrite_tree_element2675 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rewrite_tree_atom_in_rewrite_tree_element2680 = new BitSet(new long[]{0x0000000000000000L,0x000000000C000400L});
    public static final BitSet FOLLOW_ebnfSuffix_in_rewrite_tree_element2682 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rewrite_tree_in_rewrite_tree_element2716 = new BitSet(new long[]{0x0000000000000002L,0x000000000C000400L});
    public static final BitSet FOLLOW_ebnfSuffix_in_rewrite_tree_element2722 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rewrite_tree_ebnf_in_rewrite_tree_element2768 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CHAR_LITERAL_in_rewrite_tree_atom2784 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOKEN_REF_in_rewrite_tree_atom2791 = new BitSet(new long[]{0x0001000000000002L});
    public static final BitSet FOLLOW_ARG_ACTION_in_rewrite_tree_atom2793 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_REF_in_rewrite_tree_atom2814 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_LITERAL_in_rewrite_tree_atom2821 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_93_in_rewrite_tree_atom2830 = new BitSet(new long[]{0x0002040000000000L});
    public static final BitSet FOLLOW_id_in_rewrite_tree_atom2832 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ACTION_in_rewrite_tree_atom2843 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rewrite_tree_block_in_rewrite_tree_ebnf2864 = new BitSet(new long[]{0x0000000000000000L,0x000000000C000400L});
    public static final BitSet FOLLOW_ebnfSuffix_in_rewrite_tree_ebnf2866 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TREE_BEGIN_in_rewrite_tree2886 = new BitSet(new long[]{0x00023C0000000000L,0x0000000020000000L});
    public static final BitSet FOLLOW_rewrite_tree_atom_in_rewrite_tree2888 = new BitSet(new long[]{0x00023C2000000000L,0x0000000020140000L});
    public static final BitSet FOLLOW_rewrite_tree_element_in_rewrite_tree2890 = new BitSet(new long[]{0x00023C2000000000L,0x0000000020140000L});
    public static final BitSet FOLLOW_84_in_rewrite_tree2893 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_rewrite_template2925 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_82_in_rewrite_template2929 = new BitSet(new long[]{0x0002040000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_rewrite_template_args_in_rewrite_template2931 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_84_in_rewrite_template2933 = new BitSet(new long[]{0x000C000000000000L});
    public static final BitSet FOLLOW_DOUBLE_QUOTE_STRING_LITERAL_in_rewrite_template2941 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOUBLE_ANGLE_STRING_LITERAL_in_rewrite_template2947 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rewrite_template_ref_in_rewrite_template2974 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rewrite_indirect_template_head_in_rewrite_template2983 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ACTION_in_rewrite_template2992 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_rewrite_template_ref3005 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_82_in_rewrite_template_ref3009 = new BitSet(new long[]{0x0002040000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_rewrite_template_args_in_rewrite_template_ref3011 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_84_in_rewrite_template_ref3013 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_82_in_rewrite_indirect_template_head3041 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_ACTION_in_rewrite_indirect_template_head3043 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_84_in_rewrite_indirect_template_head3045 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_82_in_rewrite_indirect_template_head3047 = new BitSet(new long[]{0x0002040000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_rewrite_template_args_in_rewrite_indirect_template_head3049 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_84_in_rewrite_indirect_template_head3051 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rewrite_template_arg_in_rewrite_template_args3075 = new BitSet(new long[]{0x0000000000000002L,0x0000000000020000L});
    public static final BitSet FOLLOW_81_in_rewrite_template_args3078 = new BitSet(new long[]{0x0002040000000000L});
    public static final BitSet FOLLOW_rewrite_template_arg_in_rewrite_template_args3080 = new BitSet(new long[]{0x0000000000000002L,0x0000000000020000L});
    public static final BitSet FOLLOW_id_in_rewrite_template_arg3113 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_rewrite_template_arg3115 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_ACTION_in_rewrite_template_arg3117 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TOKEN_REF_in_id3138 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_REF_in_id3148 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rewrite_template_in_synpred1_ANTLRv32562 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rewrite_tree_alternative_in_synpred2_ANTLRv32567 = new BitSet(new long[]{0x0000000000000002L});

}