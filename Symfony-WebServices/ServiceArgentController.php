<?php


namespace AffectationBundle\Controller;

use AffectationBundle\Entity\Argent;
use AffectationBundle\Repository\ArgentRepository;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Component\Validator\Constraints\DateTime;

class ServiceArgentController extends Controller
{
    public function newAction(Request $request)
    {
        //initialiser un objet de type argent
        $argent = new Argent();

        //rensegnier les attribut de cet objet a partir de l url
        $argent->setMontant((float)$request->get('montant')/100);
        $argent->setDate(\DateTime::createFromFormat('Y-m-d', $request->get('date')));

        //persister l objet sur la bdd
        $em = $this->getDoctrine()->getManager();
        $em->persist($argent);
        $em->flush();

        //definir un objet serialiser pour normaliser notre objet (argent)
        $serializer = new Serializer([new ObjectNormalizer()]);

        //la normalisation retourne un tableaux des strings (formatted) pret pour etre encoder en format json
        $formatted = $serializer->normalize($argent);

        //encoder le tableaux normalisee (formatted) en format json et lui retourner comme retour de cette methode
        return new JsonResponse($formatted);
    }
}